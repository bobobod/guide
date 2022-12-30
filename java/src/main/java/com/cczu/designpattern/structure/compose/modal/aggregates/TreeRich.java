package com.cczu.designpattern.structure.compose.modal.aggregates;

import com.alibaba.fastjson.JSON;
import com.cczu.designpattern.structure.compose.modal.vo.TreeNode;
import com.cczu.designpattern.structure.compose.modal.vo.TreeRoot;

import java.util.Map;


/**
 * 规则树聚合
 *
 * @author yjz
 * @date 2021/11/11
 */
public class TreeRich {
    /**
     * 树根信息
     */
    private TreeRoot treeRoot;
    /**
     * 树节点id -》 子节点
     */
    private Map<Long, TreeNode> treeNodeMap;

    public TreeRich(TreeRoot treeRoot, Map<Long, TreeNode> treeNodeMap) {
        this.treeRoot = treeRoot;
        this.treeNodeMap = treeNodeMap;
    }

    public TreeRoot getTreeRoot() {
        return treeRoot;
    }

    public void setTreeRoot(TreeRoot treeRoot) {
        this.treeRoot = treeRoot;
    }

    public Map<Long, TreeNode> getTreeNodeMap() {
        return treeNodeMap;
    }

    public void setTreeNodeMap(Map<Long, TreeNode> treeNodeMap) {
        this.treeNodeMap = treeNodeMap;
    }

    @Override
    public String toString() {
        return "TreeRich{" +
                "treeRoot=" + treeRoot +
                ", treeNodeMap=" + JSON.toJSONString(treeNodeMap) +
                '}';
    }
}
