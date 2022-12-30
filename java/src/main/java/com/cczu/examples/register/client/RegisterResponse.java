package com.cczu.examples.register.client;

/**
 * 注册响应
 *
 * @author yjz
 * @date 2021/10/13
 */
public class RegisterResponse {
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";
    /**
     * 相应状态 success failure
     */
    private String status;
    /**
     * 相应吗 100、200、300
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RegisterResponse{" +
                "status='" + status + '\'' +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
