package com.alinesno.infra.ops.pipeline.entity;

import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 服务器实体类
 *
 * @author luoxiaodong
 * @version  1.0.0
 */
@TableName("server")
public class ServerEntity extends InfraBaseEntity {

    // ID
    @TableField("id")
    private Long id;

    // 名称
    @TableField("name")
    private String name;

    // IP地址
    @TableField("ip_address")
    private String ipAddress;
    // 操作系统
    @TableField("operating_system")
    private String operatingSystem;

    // CPU
    @TableField("cpu")
    private String cpu;

    // 内存
    @TableField("memory")
    private String memory;

    // 存储
    @TableField("storage")
    private String storage;

    // 状态
    @TableField("status")
    private String status;

    // 分组ID
    @TableField("group_id")
    private Long groupId;


    /**
     * 获取ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取IP地址
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * 设置IP地址
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * 获取操作系统
     */
    public String getOperatingSystem() {
        return operatingSystem;
    }

    /**
     * 设置操作系统
     */
    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    /**
     * 获取CPU
     */
    public String getCpu() {
        return cpu;
    }

    /**
     * 设置CPU
     */
    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    /**
     * 获取内存
     */
    public String getMemory() {
        return memory;
    }

    /**
     * 设置内存
     */
    public void setMemory(String memory) {
        this.memory = memory;
    }

    /**
     * 获取存储
     */
    public String getStorage() {
        return storage;
    }

    /**
     * 设置存储
     */
    public void setStorage(String storage) {
        this.storage = storage;
    }

    /**
     * 获取状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取分组ID
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * 设置分组ID
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
