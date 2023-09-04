package com.alinesno.infra.ops.pipeline.quartz.event;

import org.springframework.context.ApplicationEvent;

/**
 * JobAlarmEvent 类是任务告警事件类。
 * 该类继承自 ApplicationEvent，用于表示任务告警事件。
 *
 * 作者：luoxiaodong
 * 版本：1.0.0
 */
public class JobAlarmEvent extends ApplicationEvent {

    private Long totalCount;
    private Long transCount;
    private Long transId;
    private String transName;
    private int step;

    /**
     * 构造方法，创建一个任务告警事件对象。
     *
     * @param transId 任务ID
     */
    public JobAlarmEvent(Long transId) {
        super(transId);
        this.transId = transId;
    }

    public String getTransName() {
        return transName;
    }

    public void setTransName(String transName) {
        this.transName = transName;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public Long getTransId() {
        return transId;
    }

    public void setTransId(Long transId) {
        this.transId = transId;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getTransCount() {
        return transCount;
    }

    public void setTransCount(Long transCount) {
        this.transCount = transCount;
    }

    @Override
    public String toString() {
        return "JobAlarmEvent{" +
                "totalCount=" + totalCount +
                ", transCount=" + transCount +
                ", transId=" + transId +
                '}';
    }
}
