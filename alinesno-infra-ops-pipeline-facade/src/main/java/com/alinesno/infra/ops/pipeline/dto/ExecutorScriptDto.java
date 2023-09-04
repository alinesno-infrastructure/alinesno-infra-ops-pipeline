package com.alinesno.infra.ops.pipeline.dto;

import java.util.List;

/**
 * ExecutorScriptDto是执行脚本的数据传输对象。
 * 它包含了执行脚本的类型和脚本内容。
 */
public class ExecutorScriptDto {

    private String type; // 脚本类型
    private String scriptContent; // 脚本
    private List<ValueAttributeDto>  attributes ; // 脚本信息

    public List<ValueAttributeDto> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ValueAttributeDto> attributes) {
        this.attributes = attributes;
    }

    /**
     * 默认构造函数
     */
    public ExecutorScriptDto() {
    }

    /**
     * 获取脚本类型
     *
     * @return 脚本类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置脚本类型
     *
     * @param type 脚本类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取脚本内容
     *
     * @return 脚本内容
     */
    public String getScriptContent() {
        return scriptContent;
    }

    /**
     * 设置脚本内容
     *
     * @param scriptContent 脚本内容
     */
    public void setScriptContent(String scriptContent) {
        this.scriptContent = scriptContent;
    }

    @Override
    public String toString() {
        return "ExecutorScriptDto{" +
                "type='" + type + '\'' +
                ", scriptContent='" + scriptContent + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}
