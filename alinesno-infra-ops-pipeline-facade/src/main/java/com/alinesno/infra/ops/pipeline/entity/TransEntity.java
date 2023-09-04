package com.alinesno.infra.ops.pipeline.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 转换实体类
 * 数据表：trans
 * 表备注：存储转换信息
 *
 * @TableName 表名注解，用于指定数据库表名
 * @TableField 字段注解，用于指定数据库字段名
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("trans")
public class TransEntity extends BaseJobEntity {

    /**
     * 仓库名称
     */
    @TableField("git_id")
    private String gitId;

    /**
     * 转换文件路径
     */
    @TableField("relative_location")
    private String relativeLocation;

    /**
     * 任务的上下文
     */
    @TableField("trans_context")
    private String transContext ;

    /**
     * 关联的任务id
     */
    @TableField("job_id")
    private Long jobId ;

    /**
     * 任务排序
     */
    @TableField("order_step")
    private int orderStep ;

    /**
     * 处理完成的数据
     */
    @TableField("process_data_count")
    private Long processDataCount ;

    /**
     * 需要处理的数据量
     */
    @TableField("total_data_count")
    private Long totalDataCount ;

    /**
     * 父类步骤
     */
    @TableField("parent_step")
    private String parentStep ;

    /**
     * 插件名称
     */
    @TableField("plugin_name")
    private String pluginName ;

    /**
     * 脚本信息
     */
    @TableField("context_script")
    private String contextScript ;

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    public String getContextScript() {
        return contextScript;
    }

    public void setContextScript(String contextScript) {
        this.contextScript = contextScript;
    }

    public String getParentStep() {
        return parentStep;
    }

    public void setParentStep(String parentStep) {
        this.parentStep = parentStep;
    }

    public Long getProcessDataCount() {
        return processDataCount;
    }

    public void setProcessDataCount(Long processDataCount) {
        this.processDataCount = processDataCount;
    }

    public Long getTotalDataCount() {
        return totalDataCount;
    }

    public void setTotalDataCount(Long totalDataCount) {
        this.totalDataCount = totalDataCount;
    }

    public int getOrderStep() {
        return orderStep;
    }

    public void setOrderStep(int orderStep) {
        this.orderStep = orderStep;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getTransContext() {
        return transContext;
    }

    public void setTransContext(String transContext) {
        this.transContext = transContext;
    }

    /**
     * 获取仓库名称
     */
    public String getGitId() {
        return gitId;
    }

    /**
     * 设置仓库名称
     */
    public void setGitId(String gitId) {
        this.gitId = gitId;
    }

    /**
     * 获取转换文件路径
     */
    public String getRelativeLocation() {
        return relativeLocation;
    }

    /**
     * 设置转换文件路径
     */
    public void setRelativeLocation(String relativeLocation) {
        this.relativeLocation = relativeLocation;
    }
}