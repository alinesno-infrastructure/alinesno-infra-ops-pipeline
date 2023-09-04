package com.alinesno.infra.ops.pipeline.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 功能名： 【请填写功能名称】
 * 数据表：  job
 * 表备注：
 *
 * @TableName 表名注解，用于指定数据库表名
 * @TableField 字段注解，用于指定数据库字段名
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("job")
public class JobEntity extends BaseJobEntity {

    /**
     * 关联的转换任务id
     */
    @TableField("trans_ids")
    private String transIds;

    /**
     * 仓库名称
     */
    @TableField("git_id")
    private String gitId;

    /**
     * 作业文件路径
     */
    @TableField("relative_location")
    private String relativeLocation;

    /**
     * 任务的上下文
     */
    @TableField("job_context")
    private String jobContext ;

    public String getJobContext() {
        return jobContext;
    }

    public void setJobContext(String jobContext) {
        this.jobContext = jobContext;
    }

    /**
     * 获取关联的转换任务id
     */
    public String getTransIds() {
        return transIds;
    }

    /**
     * 设置关联的转换任务id
     */
    public void setTransIds(String transIds) {
        this.transIds = transIds;
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
     * 获取作业文件路径
     */
    public String getRelativeLocation() {
        return relativeLocation;
    }

    /**
     * 设置作业文件路径
     */
    public void setRelativeLocation(String relativeLocation) {
        this.relativeLocation = relativeLocation;
    }
}