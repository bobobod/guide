package com.cczu.examples.hdfs.namenode.server;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 内存双缓冲
 *
 * @author yjz
 */
public class DoubleBuffer {
    /**
     * 单块缓冲区默认的大小 默认 512kb
     */
    private static final int EDIT_LOG_BUFFER_LIMIT = 25 * 1024;
    /**
     * 当前这块缓冲区最大的txid
     */
    private Long startTxid = 1L;
    /**
     * 专门用来承载线程写入edits log
     */
    private EditLogBuffer currentBuffer = new EditLogBuffer();
    /**
     * 专门用来将数据同步到磁盘中取得一块缓冲
     */
    private EditLogBuffer syncBuffer = new EditLogBuffer();
    /**
     * 已经刷入磁盘的txid范围, 使用 CopyOnWriteArrayList 并发安全类
     */
    private List<String> flushedTxids = new CopyOnWriteArrayList<>();

    /**
     * 将edits log写到内存磁盘中
     *
     * @param log
     * @throws IOException
     */
    public void write(EditLog log) throws IOException {
        currentBuffer.write(log);
    }


    /**
     * 交换两块缓冲区，为了同步内存数据到磁盘
     */
    public void setReadyToSync() {
        EditLogBuffer tmp = currentBuffer;
        currentBuffer = syncBuffer;
        syncBuffer = tmp;
    }

    /**
     * 将syncbuffer中的数据刷入磁盘中
     */
    public void flush() throws IOException {
        syncBuffer.flush();
        syncBuffer.clear();
    }

    /**
     * 获取已经刷入磁盘的editsLog数据
     *
     * @return
     */
    public List<String> getFlushedTxids() {
        return flushedTxids;
    }

    /**
     * 判断一下当前的缓冲区是否写满了需要刷写到磁盘中去
     *
     * @return
     */
    public boolean shouldSyncToDisk() {
        if (currentBuffer.size() >= EDIT_LOG_BUFFER_LIMIT) {
            return true;
        }
        return false;
    }

    /**
     * 获取当前缓冲区里的数据
     *
     * @return
     */
    public String[] getBufferedEditsLog() {
        if (currentBuffer.size() == 0) {
            return new String[0];
        }
        byte[] bufferData = currentBuffer.getBufferData();
        String editsLogRawData = new String(bufferData);
        return editsLogRawData.split("\n");
    }

    /**
     * editlog缓冲区
     */
    class EditLogBuffer {
        // 写入内存缓冲，可以考虑使用list之类的，但是一般来说在这种情况下都是使用字节数组来存放
        // 又不好维护这个数据，会用一个字节数组的输出流，IO流的方式，不断的往这个流里写数据
        /**
         * 上一次flush到磁盘的时候，他的最大的txid是多少
         */
        private Long endTxid = 0L;
        /**
         * 字节数据的IO流 内存缓冲区
         */
        private ByteArrayOutputStream buffer;

        public EditLogBuffer() {
            this.buffer = new ByteArrayOutputStream(EDIT_LOG_BUFFER_LIMIT * 2);
        }

        /**
         * 将日志写入缓冲区
         *
         * @param editLog
         * @throws IOException
         */
        public void write(EditLog editLog) throws IOException {
            endTxid = editLog.getTxid();
            buffer.write(editLog.getContent().getBytes());
            buffer.write("\n".getBytes());
            System.out.println("当前缓存区的大小：" + size()
                    + "，数据内容是：" + editLog.getContent());
        }

        /**
         * 获取缓冲区大小
         *
         * @return
         */
        public int size() {
            return buffer.size();
        }

        /**
         * 刷写磁盘
         */
        public void flush() throws IOException {
            byte[] bytes = buffer.toByteArray();
            ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
            String editsLogFilePath = "/Users/yjz/IdeaProjects/priv_garden/data/dfs/editsLog/editsLog-"
                    + startTxid + "-" + endTxid + ".log";
            // 记录每次刷写磁盘的id范围
            flushedTxids.add(startTxid + "_" + endTxid);

            RandomAccessFile randomAccessFile = null;
            FileOutputStream outputStream = null;
            FileChannel editLogChannel = null;
            try {
                File file = new File(editsLogFilePath);
                if (!file.exists()) {
                    boolean bool = file.createNewFile();
                    System.out.println(editsLogFilePath + "文件创建" + bool);
                }
                randomAccessFile = new RandomAccessFile(editsLogFilePath, "rw");
                outputStream = new FileOutputStream(randomAccessFile.getFD());
                editLogChannel = outputStream.getChannel();
                // 这一步写的话，数据只会存在于os cache中
                editLogChannel.write(byteBuffer);
                // 强制把数据刷入磁盘中
                editLogChannel.force(false);
            } finally {
                if (editLogChannel != null) {
                    editLogChannel.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            }
            startTxid = endTxid + 1;
        }

        /**
         * 清空内存缓冲数据
         */
        public void clear() {
            buffer.reset();
        }

        /**
         * 获取内存缓冲区当前的数据
         *
         * @return
         */
        public byte[] getBufferData() {
            return buffer.toByteArray();
        }
    }
}