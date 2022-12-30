package com.cczu.designpattern.structure.compose.modal.vo;

/**
 * 树根信息
 *
 * @author yjz
 * @date 2021/11/12
 */
public class TreeRoot {
    /**
     * 规则树id
     */
    private Long treeId;
    /**
     * 规则树根id
     */
    private Long treeRootNodeId;
    /**
     * 规则树名称
     */
    private String treeName;

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Long getTreeRootNodeId() {
        return treeRootNodeId;
    }

    public void setTreeRootNodeId(Long treeRootNodeId) {
        this.treeRootNodeId = treeRootNodeId;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    @Override
    public String toString() {
        return "TreeRoot{" +
                "treeId=" + treeId +
                ", treeRootNodeId=" + treeRootNodeId +
                ", treeName='" + treeName + '\'' +
                '}';
    }
}
