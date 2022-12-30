package com.cczu.designpattern.structure.compose.modal.vo;

/**
 * 决策结果
 *
 * @author yjz
 * @date 2021/11/12
 */
public class EngineResult {
    /**
     * 执行结果
     */
    private boolean isSuccess;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 规则树id
     */
    private Long treeId;
    /**
     * 果实节点id
     */
    private Long nodeId;
    /**
     * 果实值
     */
    private String nodeValue;

    private EngineResult(Builder builder) {
        this.isSuccess = builder.isSuccess;
        this.userId = builder.userId;
        this.treeId = builder.treeId;
        this.nodeId = builder.nodeId;
        this.nodeValue = builder.nodeValue;
    }

    public static class Builder {
        /**
         * 执行结果
         */
        private boolean isSuccess;
        /**
         * 用户id
         */
        private String userId;
        /**
         * 规则树id
         */
        private Long treeId;
        /**
         * 果实节点id
         */
        private Long nodeId;
        /**
         * 果实值
         */
        private String nodeValue;

        public Builder isSuccess(boolean isSuccess) {
            this.isSuccess = isSuccess;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder treeId(Long treeId) {
            this.treeId = treeId;
            return this;
        }

        public Builder nodeId(Long nodeId) {
            this.nodeId = nodeId;
            return this;
        }
        public Builder nodeValue(String nodeValue){
            this.nodeValue = nodeValue;
            return this;
        }
        public EngineResult build() {
            return new EngineResult(this);
        }
    }

    public boolean isSuccess() {
        return isSuccess;
    }


    public String getUserId() {
        return userId;
    }


    public Long getTreeId() {
        return treeId;
    }


    public Long getNodeId() {
        return nodeId;
    }


    public String getNodeValue() {
        return nodeValue;
    }


}
