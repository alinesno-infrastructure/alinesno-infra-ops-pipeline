package com.alinesno.infra.ops.pipeline.event;

import org.springframework.context.ApplicationEvent;

/**
 * AlarmEvent是报警事件类，继承自ApplicationEvent。
 * 它表示一个与报警相关的事件，并包含了报警相关的属性。
 */
public class AlarmEvent extends ApplicationEvent {

    private Long totalCount;
    private Long transCount;
    private Long transId;
    private String transName;
    private int step;

    /**
     * 构造报警事件对象
     *
     * @param transId 报警事件关联的事务ID
     */
    public AlarmEvent(Long transId) {
        super(transId);
        this.transId = transId;
    }

    /**
     * 获取报警事件关联的事务ID
     *
     * @return 事务ID
     */
    public Long getTransId() {
        return transId;
    }

    /**
     * 设置报警事件关联的事务ID
     *
     * @param transId 事务ID
     */
    public void setTransId(Long transId) {
        this.transId = transId;
    }

    /**
     * 获取总计数
     *
     * @return 总计数
     */
    public Long getTotalCount() {
        return totalCount;
    }

    /**
     * 设置总计数
     *
     * @param totalCount 总计数
     */
    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 获取事务计数
     *
     * @return 事务计数
     */
    public Long getTransCount() {
        return transCount;
    }

    /**
     * 设置事务计数
     *
     * @param transCount 事务计数
     */
    public void setTransCount(Long transCount) {
        this.transCount = transCount;
    }

    /**
     * 获取事务名称
     *
     * @return 事务名称
     */
    public String getTransName() {
        return transName;
    }

    /**
     * 设置事务名称
     *
     * @param transName 事务名称
     */
    public void setTransName(String transName) {
        this.transName = transName;
    }

    /**
     * 获取步骤
     *
     * @return 步骤
     */
    public int getStep() {
        return step;
    }

    /**
     * 设置步骤
     *
     * @param step 步骤
     */
    public void setStep(int step) {
        this.step = step;
    }

    @Override
    public String toString() {
        return "AlarmEvent{" +
                "totalCount=" + totalCount +
                ", transCount=" + transCount +
                ", transId=" + transId +
                '}';
    }
}
