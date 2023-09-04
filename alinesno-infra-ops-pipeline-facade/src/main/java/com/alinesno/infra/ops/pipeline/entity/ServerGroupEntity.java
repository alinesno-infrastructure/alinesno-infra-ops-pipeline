package com.alinesno.infra.ops.pipeline.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 服务器组实体类
 */
@TableName("server_group")
public class ServerGroupEntity extends InfraBaseEntity {
    @TableField("id")
    private Long id;

    @TableField("name")
    private String name;

    @TableField("description")
    private String description;

    /**
     * 获取服务器组ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置服务器组ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取服务器组名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置服务器组名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取服务器组描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置服务器组描述
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
