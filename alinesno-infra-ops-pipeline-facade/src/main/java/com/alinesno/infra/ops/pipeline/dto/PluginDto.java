package com.alinesno.infra.ops.pipeline.dto;

import java.util.List;

/**
 * 实体类信息
 */
public class PluginDto {

    /**
     * 名称
     */
    private String name ;

    /**
     * 脚本信息
     */
    private String contextScript ;

    /**
     * 插件属性
     */
    private List<ValueAttributeDto> pluginAttributes ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContextScript() {
        return contextScript;
    }

    public void setContextScript(String contextScript) {
        this.contextScript = contextScript;
    }

    public List<ValueAttributeDto> getPluginAttributes() {
        return pluginAttributes;
    }

    public void setPluginAttributes(List<ValueAttributeDto> pluginAttributes) {
        this.pluginAttributes = pluginAttributes;
    }
}
