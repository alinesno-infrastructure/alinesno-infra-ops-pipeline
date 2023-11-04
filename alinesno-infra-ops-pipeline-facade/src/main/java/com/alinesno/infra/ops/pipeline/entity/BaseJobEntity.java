package com.alinesno.infra.ops.pipeline.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * kettle属性的基础实现类
 * 
 * @TableName 表名注解，用于指定数据库表名
 * @TableField 字段注解，用于指定数据库字段名
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@Data
public class BaseJobEntity extends InfraBaseEntity {

    /**
     * 名称
     */
    @TableField("name")
	@ColumnType(length=255)
	@ColumnComment("名称")
    private String name;
    
    /**
     * 描述
     */
    @TableField("description")
	@ColumnType(length=255)
	@ColumnComment("描述")
    private String description;
   
    /**
     * 类型
     */
    @TableField("type")
	@ColumnType(length=255)
	@ColumnComment("类型")
    private String type;
  
    /**
     * 路径
     */
    @TableField("path")
	@ColumnType(length=255)
	@ColumnComment("路径")
    private String path;
   
    /**
     * Quartz
     */
    @TableField("quartz")
	@ColumnType(length=255)
	@ColumnComment("Quartz")
    private String quartz;
  
    /**
     * 同步策略
     */
    @TableField("strategy")
	@ColumnType(length=20)
	@ColumnComment("同步策略")
    private String syncStrategy;
  
    /**
     * 日志级别
     */
    @TableField("log_level")
	@ColumnType(length=10)
	@ColumnComment("日志级别")
    private String logLevel;
  
    /**
     * 状态
     */
    @TableField("status")
	@ColumnType(length=1)
	@ColumnComment("状态")
    private String status;

    /**
     * 分类ID
     */
    @TableField("category_id")
	@ColumnType(length=10)
	@ColumnComment("分类ID")
    private String categoryId;
}
