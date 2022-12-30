package com.cczu.examples.hdfs.backupnode.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * fsimage 文件的checkpoint组件
 *
 * @author yjz
 * @date 2022/1/13
 */
public class FsCheckPointer extends Thread {

    private static final Integer CHECKPOINT_INTERVAL = 2 * 60 * 1000;
    private BackupNode backupNode;
    private FSNameSystem nameSystem;
    private String lastFsImageFile;

    public FsCheckPointer(BackupNode backupNode, FSNameSystem nameSystem) {
        this.backupNode = backupNode;
        this.nameSystem = nameSystem;
    }

    @Override
    public void run() {
        System.out.println("checkPoint 定时调度线程启动");
        while (backupNode.isRunning()) {
            try {
                Thread.sleep(CHECKPOINT_INTERVAL);
                FsImage fsImage = nameSystem.getFsImageJson();
                if (fsImage != null) {
                    // 先删除上一个fsimage
                    removeLastFsImageFile();
                    // checkpoint
                    doCheckpoint(fsImage);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void doCheckpoint(FsImage fsImage) throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.wrap(fsImage.getFsImageJson().getBytes());
        String fsImageFile = "/Users/yjz/IdeaProjects/priv_garden/data/dfs/fsimage/fsimage-" + fsImage.getMaxTxid() + ".meta";
        System.out.println("准备生成fsimage文件：" + fsImageFile);
        lastFsImageFile = fsImageFile;
        RandomAccessFile randomAccessFile = null;
        FileOutputStream outputStream = null;
        FileChannel editLogChannel = null;
        try {
            File file = new File(fsImageFile);
            if (!file.exists()) {
                boolean bool = file.createNewFile();
                System.out.println(fsImageFile + "文件创建" + bool);
            }
            randomAccessFile = new RandomAccessFile(fsImageFile, "rw");
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
    }

    /**
     * 删除上一个fsimage磁盘文件
     *
     * @throws Exception
     */
    private void removeLastFsImageFile() throws Exception {
        if (lastFsImageFile == null) {
            return;
        }
        File file = new File(lastFsImageFile);
        if (file.exists()) {
            file.delete();
        }
    }
}
