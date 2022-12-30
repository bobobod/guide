package com.cczu.examples.hdfs.namenode.server;


import java.io.IOException;
import java.util.List;

/**
 * 负责管理fs中editlog 日志组件
 *
 * @author yjz
 * @date 2021/12/30
 */
public class FSEditlog {
    /**
     * 当前递增到的txid的序号
     */
    private long txidSeq = 0L;
    /**
     * 内存双缓冲区
     */
    private DoubleBuffer doubleBuffer = new DoubleBuffer();
    /**
     * 当前是否将内存缓冲刷入磁盘中
     */
    private volatile Boolean isSyncRunning = false;
//    /**
//     * 当前是否有线程在等待刷新下一批edits log到磁盘里去
//     */
//    private volatile Boolean isWaitSync = false;
    /**
     * 是否正在调度刷盘的操作
     */
    private volatile Boolean isSchedulingSync = false;
    /**
     * 同步到磁盘中最大的txid
     */
    private volatile Long syncTxid = 0L;
    /**
     * 每个线程本地的txid副本
     */
    private ThreadLocal<Long> localTxid = new ThreadLocal<>();


    /**
     * 记录操作日志
     *
     * @param content 日志
     */
    public void logEdit(String content) {
        // 这里直接加锁
        synchronized (this) {
            // 检查是否有人正在调度一次刷盘的操作
            waitSchedulingSync();
            // 获取全局唯一的txid
            txidSeq++;
            long txid = txidSeq;
            localTxid.set(txid);
            // 构造一条edits log 对象
            EditLog log = new EditLog(txid, content);
            // 将edits log 写入内存缓冲中，不是直接刷入磁盘
            try {
                doubleBuffer.write(log);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 每次写完缓冲区之后，检查缓冲区是否满了
            if (!doubleBuffer.shouldSyncToDisk()) {
                return;
            }
            // 需要进行刷磁盘了
            isSchedulingSync = true;
        }
        logSync();
    }

    /**
     * 等待正在调度刷磁盘的操作
     */
    private void waitSchedulingSync() {
        try {
            while (isSchedulingSync) {
                // 此时释放锁，等待一秒再次获取锁  isSchedulingSync=false
                wait(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 将内存缓冲内的数据刷入磁盘文件中
     * 在这里尝试允许某一个线程一次性将内存缓冲中的数据刷入磁盘文件中
     */
    private void logSync() {
        // 再次加锁
        synchronized (this) {
            Long txid = localTxid.get();
            // 如果说当前有人正好在刷内存缓冲到磁盘中
            if (isSyncRunning) {
                // 此处进行判断
                // 假如说某个线程已经把 1 2 3 4 5 的edits log都从syncbuffer刷入磁盘了，或者说正常刷入磁盘，此时maxTxid为5，为磁盘最大的txid
                // 此时再来一个线程，他对应的txid =2 ，那么他可以直接返回了，代表他对应的edits log已经被别的线程写入磁盘了
                localTxid.remove();
                if (txid <= syncTxid) {
                    return;
                }
                // 假如此时来了个 txid = 6 的线程
                // 他需要做一下等待，同时需要释放锁
                // 如果此时有线程在等待刷写磁盘的话，直接返回
                // 接下来，如果此时再来一个txid =9 的线程的话，已经有线程在等待刷写磁盘，他就可以继续下一批数据写入了
                // 保证只有一个线程在等待刷写磁盘

                // 此时线程的txid=80，需要等待别人先刷完
                try {
                    while (isSyncRunning) {
                        // 占用2s
                        wait(2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 交换两块缓冲区
            doubleBuffer.setReadyToSync();
            // 保存一下当前要同步到磁盘中去的最大的txid
            // 此时editlogBuffer 中的syncBuffer这块区域，交换完这里可能有多条数据
            // 而且他里面的edits log的txid一定是从小到大的
            syncTxid = txid;
            // 设置当前正在同步磁盘的标识位
            isSchedulingSync = false;
            // 唤醒那些还卡在循环那里的线程
            notifyAll();
            isSyncRunning = true;
        }
        // 开始同步内存缓冲到磁盘文件中，不加锁
        // 这个过程是很慢的，基本上是毫秒级别的
        try {
            doubleBuffer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            // 同步完磁盘后，将标志位复位，在释放锁
            isSyncRunning = false;
            // 唤醒可能正在同步磁盘的线程
            notifyAll();
        }
    }

    /**
     * 强制把内存缓冲刷写如磁盘中
     */
    public void flush() {
        try {
            // 先切换为syncbuffer
            doubleBuffer.setReadyToSync();
            doubleBuffer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取已经刷入磁盘的editsLog数据
     *
     * @return
     */
    public List<String> getFlushedTxids() {
        return doubleBuffer.getFlushedTxids();
    }

    /**
     * 获取当前缓冲区里的数据
     *
     * @return
     */
    public String[] getBufferedEditsLog() {
        synchronized (this) {
            return doubleBuffer.getBufferedEditsLog();
        }
    }
}
