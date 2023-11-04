package com.alinesno.infra.ops.pipeline.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
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
@Data
public class JobEntity extends BaseJobEntity {

    /**
     * 关联的转换任务id
     */
    @TableField("trans_ids")
	@ColumnType(length=255)
	@ColumnComment("关联的转换任务id")
    private String transIds;

    /**
     * 仓库名称
     */
    @TableField("git_id")
	@ColumnType(length=20)
	@ColumnComment("仓库名称")
    private String gitId;

    /**
     * 作业文件路径
     */
    @TableField("relative_location")
	@ColumnType(length=255)
	@ColumnComment("作业文件路径")
    private String relativeLocation;

    /**
     * 任务的上下文
     */
    @TableField("job_context")
	@ColumnType(length=255)
	@ColumnComment("任务的上下文")
    private String jobContext ;
}
