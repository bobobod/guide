package com.cczu.designpattern.structure.compose.service.logic;


import com.cczu.designpattern.structure.compose.modal.vo.TreeNodeLink;

import java.util.List;
import java.util.Map;

/**
 * 决策抽象类提供基础服务
 *
 * @author yjz
 * @date 2021/11/12
 */
public abstract class BaseLogic implements LogicFilter {
    @Override
    public Long filter(String matterValue, List<TreeNodeLink> treeNodeLinks) {
        for (TreeNodeLink treeNodeLink : treeNodeLinks) {
            if (decisionLogic(matterValue, treeNodeLink)) {
                return treeNodeLink.getNodeIdTo();
            }
        }
        return 0L;
    }

    @Override
    public abstract String matterValue(Long treeId, String userId, Map<String, String> decisionMatter);

    private boolean decisionLogic(String matterValue, TreeNodeLink nodeLink) {
        switch (nodeLink.getRuleLimitType()) {
            case 1:
                return matterValue.equals(nodeLink.getRuleLimitValue());
            case 2:
                return Double.parseDouble(matterValue) > Double.parseDouble(nodeLink.getRuleLimitValue());
            case 3:
                return Double.parseDouble(matterValue) < Double.parseDouble(nodeLink.getRuleLimitValue());
            case 4:
                return Double.parseDouble(matterValue) >= Double.parseDouble(nodeLink.getRuleLimitValue());
            case 5:
                return Double.parseDouble(matterValue) <= Double.parseDouble(nodeLink.getRuleLimitValue());
            default:
                return false;
        }
    }
}
