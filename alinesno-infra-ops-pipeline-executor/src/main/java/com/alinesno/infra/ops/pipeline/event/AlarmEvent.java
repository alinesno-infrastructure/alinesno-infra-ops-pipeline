package com.alinesno.infra.ops.pipeline.event;

import org.springframework.context.ApplicationEvent;

/**
 * TransEvent 事件
 */
public class AlarmEvent extends ApplicationEvent {

    private Long totalCount ;
    private Long transCount;
    private Long transId ;
    private String transName ;
    private int step ;

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

    public AlarmEvent(Long transId) {
        super(transId);
        this.transId = transId ;
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
        return "TransEvent{" +
                "totalCount=" + totalCount +
                ", transCount=" + transCount +
                ", transId=" + transId +
                '}';
    }
}