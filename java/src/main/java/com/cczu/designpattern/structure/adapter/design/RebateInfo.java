package com.cczu.designpattern.structure.adapter.design;

import java.util.Date;

/**
 * 消息统一类
 *
 * @author yjz
 * @date 2021/11/11
 */
public class RebateInfo {
    private String userId;
    private String bizId;
    private Date bizTime;
    private String desc;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public Date getBizTime() {
        return bizTime;
    }

    public void setBizTime(String bizTime) {
        this.bizTime = new Date(Long.parseLong("1591077840669"));
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
