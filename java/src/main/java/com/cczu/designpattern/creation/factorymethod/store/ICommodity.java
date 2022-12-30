package com.cczu.designpattern.creation.factorymethod.store;

import java.util.Map;

public interface ICommodity {
    void sendCommodity(String uid, String commodityId, String bizId, Map<String, String> extMap) throws Exception;
}
