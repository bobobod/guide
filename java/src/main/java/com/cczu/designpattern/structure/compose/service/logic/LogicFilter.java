package com.cczu.designpattern.structure.compose.service.logic;

import com.cczu.designpattern.structure.compose.modal.vo.TreeNodeLink;

import java.util.List;
import java.util.Map;

/**
 * 树节点逻辑过滤器
 *
 * @author yjz
 * @date 2021/11/12
 */
public interface LogicFilter {
    /**
     * 逻辑决策器
     *
     * @param matterValue   决策值
     * @param treeNodeLinks 决策节点
     * @return 下一个节点id
     */
    Long filter(String matterValue, List<TreeNodeLink> treeNodeLinks);

    /**
     * 获取决策值
     *
     * @param treeId
     * @param userId
     * @param decisionMatter 决策物料
     * @return 决策值
     */
    String matterValue(Long treeId, String userId, Map<String, String> decisionMatter);
}
