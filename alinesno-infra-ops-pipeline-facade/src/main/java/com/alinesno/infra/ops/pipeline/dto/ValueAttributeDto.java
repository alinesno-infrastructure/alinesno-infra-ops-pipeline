package com.alinesno.infra.ops.pipeline.dto;

/**
 * ValueAttributeDto 类是环境变量的数据传输对象。
 * 用于存储环境变量的键值对。
 */
public class ValueAttributeDto {

    private String key; // 环境变量的键
    private String value; // 环境变量的值

    public ValueAttributeDto() {
    }

    public ValueAttributeDto(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
