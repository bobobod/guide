package com.cczu.designpattern.structure.facade.door.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yjz
 * @date 2021/11/14
 */
@ConfigurationProperties("it.door")
public class StarterServiceProperties {
    private String userStr;

    public String getUserStr() {
        return userStr;
    }

    public void setUserStr(String userStr) {
        this.userStr = userStr;
    }
}
