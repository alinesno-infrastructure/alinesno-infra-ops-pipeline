package com.alinesno.infra.ops.pipeline.entity;

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
public class BaseJobEntity extends InfraBaseEntity {

    /**
     * 名称
     */
    @TableField("name")
    private String name;
    
    /**
     * 描述
     */
    @TableField("description")
    private String description;
   
    /**
     * 类型
     */
    @TableField("type")
    private String type;
  
    /**
     * 路径
     */
    @TableField("path")
    private String path;
   
    /**
     * Quartz
     */
    @TableField("quartz")
    private String quartz;
  
    /**
     * 同步策略
     */
    @TableField("strategy")
    private String syncStrategy;
  
    /**
     * 日志级别
     */
    @TableField("log_level")
    private String logLevel;
  
    /**
     * 状态
     */
    @TableField("status")
    private String status;

    /**
     * 分类ID
     */
    @TableField("category_id")
    private String categoryId;

    /**
     * 获取名称
     * 
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     * 
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取描述
     * 
     * @return 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     * 
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取类型
     * 
     * @return 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型
     * 
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取路径
     * 
     * @return 路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置路径
     * 
     * @param path 路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取Quartz
     * 
     * @return Quartz
     */
    public String getQuartz() {
        return quartz;
    }

    /**
     * 设置Quartz
     * 
     * @param quartz Quartz
     */
    public void setQuartz(String quartz) {
        this.quartz = quartz;
    }

    /**
     * 获取同步策略
     * 
     * @return 同步策略
     */
    public String getSyncStrategy() {
        return syncStrategy;
    }

    /**
     * 设置同步策略
     * 
     * @param syncStrategy 同步策略
     */
    public void setSyncStrategy(String syncStrategy) {
        this.syncStrategy = syncStrategy;
    }

    /**
     * 获取日志级别
     * 
     * @return 日志级别
     */
    public String getLogLevel() {
        return logLevel;
    }

    /**
     * 设置日志级别
     * 
     * @param logLevel 日志级别
     */
    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    /**
     * 获取状态
     * 
     * @return 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     * 
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取分类ID
     * 
     * @return 分类ID
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * 设置分类ID
     * 
     * @param categoryId 分类ID
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

}