package com.alinesno.infra.ops.pipeline.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
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
@Data
public class TransEntity extends BaseJobEntity {

    /**
     * 仓库名称
     */
    @TableField("git_id")
	@ColumnType(length=50)
	@ColumnComment("仓库名称")
    private String gitId;

    /**
     * 转换文件路径
     */
    @TableField("relative_location")
	@ColumnType(length=255)
	@ColumnComment("转换文件路径")
    private String relativeLocation;

    /**
     * 任务的上下文
     */
    @TableField("trans_context")
	@ColumnType(length=255)
	@ColumnComment("任务的上下文")
    private String transContext ;

    /**
     * 关联的任务id
     */
    @TableField("job_id")
	@ColumnType(length=255)
	@ColumnComment("关联的任务id")
    private Long jobId ;

    /**
     * 任务排序
     */
    @TableField("order_step")
	@ColumnType(length=2)
	@ColumnComment("任务排序")
    private int orderStep ;

    /**
     * 处理完成的数据
     */
    @TableField("process_data_count")
	@ColumnType(length=10)
	@ColumnComment("处理完成的数据")
    private Long processDataCount ;

    /**
     * 需要处理的数据量
     */
    @TableField("total_data_count")
	@ColumnType(length=10)
	@ColumnComment("需要处理的数据量")
    private Long totalDataCount ;

    /**
     * 父类步骤
     */
    @TableField("parent_step")
	@ColumnType(length=255)
	@ColumnComment("父类步骤")
    private String parentStep ;

    /**
     * 插件名称
     */
    @TableField("plugin_name")
	@ColumnType(length=50)
	@ColumnComment("插件名称")
    private String pluginName ;

    /**
     * 脚本信息
     */
    @TableField("context_script")
	@ColumnType(length=255)
	@ColumnComment("脚本信息")
    private String contextScript ;
}
