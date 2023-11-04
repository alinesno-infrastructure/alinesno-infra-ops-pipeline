package com.alinesno.infra.ops.pipeline.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
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
@Data
public class ServerEntity extends InfraBaseEntity {

    // ID
    @TableField("id")
	@ColumnType(length=10)
	@ColumnComment("ID")
    private Long id;

    // 名称
    @TableField("name")
	@ColumnType(length=255)
	@ColumnComment("名称")
    private String name;

    // IP地址
    @TableField("ip_address")
	@ColumnType(length=15)
	@ColumnComment("IP地址")
    private String ipAddress;
    // 操作系统
    @TableField("operating_system")
	@ColumnType(length=50)
	@ColumnComment("操作系统")
    private String operatingSystem;

    // CPU
    @TableField("cpu")
	@ColumnType(length=32)
	@ColumnComment("CPU")
    private String cpu;

    // 内存
    @TableField("memory")
	@ColumnType(length=256)
	@ColumnComment("内存")
    private String memory;

    // 存储
    @TableField("storage")
	@ColumnType(length=255)
	@ColumnComment("存储")
    private String storage;

    // 状态
    @TableField("status")
	@ColumnType(length=20)
	@ColumnComment("状态")
    private String status;

    // 分组ID
    @TableField("group_id")
	@ColumnType(length=50)
	@ColumnComment("分组ID")
    private Long groupId;
}
