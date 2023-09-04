package com.alinesno.infra.ops.pipeline.dto;

import java.io.Serializable;
import java.util.List;

/**
 * TaskInfoDto是任务信息的数据传输对象。
 * 它包含了任务的类型、名称和描述。
 */
public class TaskInfoDto implements Serializable {

    private String taskType; // 任务类型
    private String name;  // 任务名称
    private String describe; // 任务描述

    private List<ValueAttributeDto> settings ; // 参数配置
    private List<ValueAttributeDto> env ; // 环境列表
    private List<TaskStepInfo> workflow ; // 流程信息

    /**
     * 获取任务类型
     *
     * @return 任务类型
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * 设置任务类型
     *
     * @param taskType 任务类型
     */
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    /**
     * 获取任务名称
     *
     * @return 任务名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置任务名称
     *
     * @param name 任务名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取任务描述
     *
     * @return 任务描述
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 设置任务描述
     *
     * @param describe 任务描述
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public List<TaskStepInfo> getSteps() {
        return workflow;
    }

    public void setSteps(List<TaskStepInfo> workflow) {
        this.workflow = workflow;
    }

    public List<ValueAttributeDto> getSettings() {
        return settings;
    }

    public void setSettings(List<ValueAttributeDto> settings) {
        this.settings = settings;
    }

    public List<ValueAttributeDto> getEnv() {
        return env;
    }

    public void setEnv(List<ValueAttributeDto> env) {
        this.env = env;
    }

    public List<TaskStepInfo> getWorkflow() {
        return workflow;
    }

    public void setWorkflow(List<TaskStepInfo> workflow) {
        this.workflow = workflow;
    }
}
