package com.cczu.designpattern.structure.compose.modal.vo;

import java.util.List;

/**
 * 规则数节点信息
 *
 * @author yjz
 * @date 2021/11/12
 */
public class TreeNode {
    /**
     * 规则树id
     */
    private Long treeId;
    /**
     * 规则节点id
     */
    private Long treeNodeId;
    /**
     * 节点类型 1叶子 2果实
     */
    private Integer nodeType;
    /**
     * 节点值【nodeType=2】 果实值
     */
    private String nodeValue;
    /**
     * 规则key
     */
    private String ruleKey;
    /**
     * 规则描述
     */
    private String ruleDesc;
    /**
     * 节点链路
     */
    private List<TreeNodeLink> treeNodeLinkList;

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Long getTreeNodeId() {
        return treeNodeId;
    }

    public void setTreeNodeId(Long treeNodeId) {
        this.treeNodeId = treeNodeId;
    }

    public Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    public String getRuleKey() {
        return ruleKey;
    }

    public void setRuleKey(String ruleKey) {
        this.ruleKey = ruleKey;
    }

    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
    }

    public List<TreeNodeLink> getTreeNodeLinkList() {
        return treeNodeLinkList;
    }

    public void setTreeNodeLinkList(List<TreeNodeLink> treeNodeLinkList) {
        this.treeNodeLinkList = treeNodeLinkList;
    }
}
