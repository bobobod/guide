package com.cczu.examples.hdfs.backupnode.server;

/**
 * @author yjz
 * @date 2022/1/13
 */
public class FsImage {
    private long maxTxid;
    private String fsImageJson;

    public FsImage(long maxTxid, String fsImageJson) {
        this.maxTxid = maxTxid;
        this.fsImageJson = fsImageJson;
    }

    public long getMaxTxid() {
        return maxTxid;
    }

    public void setMaxTxid(long maxTxid) {
        this.maxTxid = maxTxid;
    }

    public String getFsImageJson() {
        return fsImageJson;
    }

    public void setFsImageJson(String fsImageJson) {
        this.fsImageJson = fsImageJson;
    }

    @Override
    public String toString() {
        return "FsImage{" +
                "maxTxid=" + maxTxid +
                ", fsImageJson='" + fsImageJson + '\'' +
                '}';
    }
}
