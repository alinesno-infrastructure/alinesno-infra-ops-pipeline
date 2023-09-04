package com.alinesno.infra.ops.pipeline.enums;

/**
 * 步骤类型枚举
 */
public enum StepTypeEnums {

    STOP("stop", "停止步骤"),
    NODE("node", "运行节点"),
    START("start", "开始步骤"),
    IF("if", "条件步骤"),
    END("end", "结束步骤"),
    CONDITION("condition", "条件步骤");

    private final String code;
    private final String description;

    StepTypeEnums(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static StepTypeEnums getEnumByCode(String code) {
        for (StepTypeEnums enumValue : StepTypeEnums.values()) {
            if (enumValue.getCode().equals(code)) {
                return enumValue;
            }
        }
        return null; // 如果没有匹配的枚举常量，则返回null或抛出异常，根据实际情况决定
    }
}
