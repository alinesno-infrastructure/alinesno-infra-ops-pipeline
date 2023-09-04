package com.alinesno.infra.ops.pipeline.enums;

/**
 * 任务状态枚举
 */
public enum JobStatusEnums {
    /**
     * 表示任务处于待处理状态
     */
    PENDING("pending", "待处理"),

    /**
     * 表示任务处于处理中状态
     */
    PROCESSING("processing", "处理中"),

    /**
     * 表示任务已完成
     */
    COMPLETED("completed", "已完成"),

    /**
     * 表示任务失败
     */
    FAILED("failed", "失败");

    private final String status;
    private final String label;

    JobStatusEnums(String status, String label) {
        this.status = status;
        this.label = label;
    }

    public String getStatus() {
        return status;
    }

    public String getLabel() {
        return label;
    }

    public static JobStatusEnums getEnumByStatus(String status) {
        for (JobStatusEnums enumValue : JobStatusEnums.values()) {
            if (enumValue.getStatus().equals(status)) {
                return enumValue;
            }
        }
        return null; // 如果没有匹配的枚举常量，则返回null或抛出异常，根据实际情况决定
    }
}
