package com.cczu.examples.hdfs.namenode.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 代表一条edits log
 */
public class EditLog {
    private long txid;
    private String content;

    public EditLog(long txid, String content) {
        this.txid = txid;
        JSONObject object = JSON.parseObject(content);
        object.put("txid", txid);
        this.content = JSON.toJSONString(object);
    }

    public long getTxid() {
        return txid;
    }

    public void setTxid(long txid) {
        this.txid = txid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "EditLog{" +
                "txid=" + txid +
                ", content='" + content + '\'' +
                '}';
    }
}