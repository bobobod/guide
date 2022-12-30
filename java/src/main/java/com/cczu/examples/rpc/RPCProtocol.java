package com.cczu.examples.rpc;

/**
 * @author yjz
 * @date 2021/12/19
 */
public interface RPCProtocol {
    long versionID = 666L;
    void mkdirs(String path);
}
