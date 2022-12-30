package com.cczu.designpattern.behavior.state;

import java.util.Date;

/**
 * @author yjz
 * @date 2022/2/6
 */
public class ActivityInfo {
    private String activityId;// 活动Id
    private String activityName;// 活动名称
    private Enum<Status> status;// 状态
    private Date beginTime;// 开始时间
    private Date endTime;// 结束时间

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Enum<Status> getStatus() {
        return status;
    }

    public void setStatus(Enum<Status> status) {
        this.status = status;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
