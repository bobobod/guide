package com.cczu.examples.hdfs.client;

/**
 * 文件系统的接口
 *
 * @author yjz
 */
public interface FileSystem {
    /**
     * 创建目录
     *
     * @param path
     * @throws Exception
     */
    void mkdir(String path) throws Exception;

    /**
     * 优雅关闭
     *
     * @throws Exception
     */
    void shutdown() throws Exception;
}
