package com.cczu.examples.hdfs.namenode.server;

/**
 * 负责管理元数据的核心组件
 *
 * @author yjz
 * @date 2021/12/30
 */
public class FSNameSystem {
    /**
     * 负责管理内存文件目录数组件
     */
    private FSDirectory fsDirectory;
    /**
     * 负责管理edits log写入磁盘的组件
     */
    private FSEditlog fsEditlog;

    public FSNameSystem() {
        this.fsDirectory = new FSDirectory();
        this.fsEditlog = new FSEditlog();
    }

    /**
     * 创建目录
     *
     * @param path 路径
     * @return 创建成功
     * @throws Exception
     */
    public Boolean mkdir(String path) throws Exception {
        this.fsDirectory.mkdir(path);
        this.fsEditlog.logEdit("{'OP':'MKDIR','PATH': '" + path + "'}");
        return true;
    }

    /**
     * 强制把内存数据刷入磁盘中
     */
    public void flush() {
        this.fsEditlog.flush();
    }

    /**
     * 获取一个fsEditsLog组件
     *
     * @return
     */
    public FSEditlog getFsEditLog() {
        return fsEditlog;
    }
}
