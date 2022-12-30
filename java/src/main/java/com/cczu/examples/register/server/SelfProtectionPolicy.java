package com.cczu.examples.register.server;

/**
 * 自我保护机制
 *
 * @author yjz
 * @date 2021/12/28
 */
public class SelfProtectionPolicy {
    /**
     * 期望的一个心跳次数，如果你有10个服务实例，这个数值就是 10 * 2 = 20
     */
    private long expectedHeartbeatRate;
    /**
     * 期望的心跳次数阈值 10*2*0.85 = 17 每分钟至少有17次，才不进入自我保护机制
     */
    private long expectedHeartbeatThreshold;

    private static SelfProtectionPolicy instance = new SelfProtectionPolicy();

    private SelfProtectionPolicy() {

    }

    public static SelfProtectionPolicy getInstance() {
        return instance;
    }

    public long getExpectedHeartbeatRate() {
        return expectedHeartbeatRate;
    }

    public void setExpectedHeartbeatRate(long expectedHeartbeatRate) {
        this.expectedHeartbeatRate = expectedHeartbeatRate;
    }

    public long getExpectedHeartbeatThreshold() {
        return expectedHeartbeatThreshold;
    }

    public void setExpectedHeartbeatThreshold(long expectedHeartbeatThreshold) {
        this.expectedHeartbeatThreshold = expectedHeartbeatThreshold;
    }

    /**
     * s是否开启自我保护
     *
     * @return
     */
    public Boolean isEnable() {
        HeartbeatMessuredRate heartbeatMessuredRate = HeartbeatMessuredRate.getInstance();
        long latestMinuteHeartbeatRate = heartbeatMessuredRate.get();
        if (latestMinuteHeartbeatRate < this.expectedHeartbeatThreshold) {
            System.out.println("【自我保护机制开启了】 最近一次心跳次数：" + latestMinuteHeartbeatRate + ",期望心跳次数：" + expectedHeartbeatThreshold);
            return true;
        }
        System.out.println("【自我保护机制未开启】 最近一次心跳次数：" + latestMinuteHeartbeatRate + ",期望心跳次数：" + expectedHeartbeatThreshold);
        return false;
    }
}
