package com.cczu.examples.register.client;

/**
 * 心跳响应状态
 *
 * @author yjz
 * @date 2021/10/13
 */
public class HeartbeatResponse {
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";
    /**
     * 相应状态 success failure
     */
    private String status;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "HeartbeatResponse{" +
                "status='" + status + '\'' +
                '}';
    }
}
