package com.cczu.designpattern.structure.compose.service.logic.impl;


import com.cczu.designpattern.structure.compose.service.logic.BaseLogic;

import java.util.Map;

/**
 * 年龄节点
 * @author yjz
 * @date 2021/11/12
 */
public class UserAgeFilter extends BaseLogic {
    @Override
    public String matterValue(Long treeId, String userId, Map<String, String> decisionMatter) {
        return decisionMatter.get("age");
    }
}
