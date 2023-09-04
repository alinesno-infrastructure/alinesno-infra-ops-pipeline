package com.alinesno.infra.ops.pipeline.dto;

import java.io.Serializable;

public class TaskStepInfo implements Serializable {

    private String name ;
    private String id ;
    private String type ;
    private PluginDto plugin ;
    private String parentStep ;

    public String getParentStep() {
        return parentStep;
    }

    public void setParentStep(String parentStep) {
        this.parentStep = parentStep;
    }

    public PluginDto getPlugin() {
        return plugin;
    }

    public void setPlugin(PluginDto plugin) {
        this.plugin = plugin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
