package com.cczu.examples.hdfs.backupnode.server;

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


    public FSNameSystem() {
        this.fsDirectory = new FSDirectory();
    }

    /**
     * 创建目录
     *
     * @param path 路径
     * @return 创建成功
     * @throws Exception
     */
    public Boolean mkdir(long txid,String path) throws Exception {
        this.fsDirectory.mkdir(txid,path);
        return true;
    }

    /**
     * 获取文件目录树json
     *
     * @return
     * @throws Exception
     */
    public FsImage getFsImageJson() throws Exception {
        return this.fsDirectory.getFsImage();
    }

}
