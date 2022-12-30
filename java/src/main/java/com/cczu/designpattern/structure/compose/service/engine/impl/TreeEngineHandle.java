package com.cczu.designpattern.structure.compose.service.engine.impl;


import com.cczu.designpattern.structure.compose.modal.aggregates.TreeRich;
import com.cczu.designpattern.structure.compose.modal.vo.EngineResult;
import com.cczu.designpattern.structure.compose.modal.vo.TreeNode;
import com.cczu.designpattern.structure.compose.service.engine.EngineBase;

import java.util.Map;

/**
 * @author yjz
 * @date 2021/11/12
 */
public class TreeEngineHandle extends EngineBase {
    @Override
    public EngineResult process(Long treeId, String userId, TreeRich treeRich, Map<String, String> decisionMatter) {
        // 决策流程
        TreeNode treeNode = engineDecisionMaker(treeRich, treeId, userId, decisionMatter);

        return new EngineResult.Builder()
                .userId(userId)
                .treeId(treeId)
                .nodeId(treeNode.getTreeNodeId())
                .nodeValue(treeNode.getNodeValue())
                .build();
    }
}
