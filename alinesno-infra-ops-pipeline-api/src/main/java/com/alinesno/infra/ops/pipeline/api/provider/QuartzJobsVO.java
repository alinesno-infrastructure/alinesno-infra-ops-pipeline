package com.alinesno.infra.ops.pipeline.api.provider;

/**
 * QuartzJobsVO是表示Quartz作业的值对象，用于存储作业的相关信息。
 * 它包含作业的名称、组名、Cron表达式或触发器的键以及作业或触发器的状态。
 */
public class QuartzJobsVO {
    private String jobDetailName; // JobDetail的名称
    private String groupName; // JobDetail所属的组名
    private String jobCronExpression; // Job的Cron表达式或者触发器的键
    private String status; // Job或Trigger的状态

    public String getJobDetailName() {
        return jobDetailName;
    }

    public void setJobDetailName(String jobDetailName) {
        this.jobDetailName = jobDetailName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getJobCronExpression() {
        return jobCronExpression;
    }

    public void setJobCronExpression(String jobCronExpression) {
        this.jobCronExpression = jobCronExpression;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
