package com.cczu.designpattern.structure.facade.door.config;

import org.springframework.util.StringUtils;

/**
 * @author yjz
 * @date 2021/11/14
 */
public class StarterService {
    private String userStr;

    public StarterService(String userStr) {
        this.userStr = userStr;
    }

    public String[] split(String separatorChar) {
        return StringUtils.split(this.userStr, separatorChar);
    }
}
