package com.alinesno.infra.ops.pipeline.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 服务器组实体类
 */
@TableName("server_group")
@Data
public class ServerGroupEntity extends InfraBaseEntity {
    @TableField("id")
	@ColumnType(length=10)
	@ColumnComment("编号")
    private Long id;

    @TableField("name")
	@ColumnType(length=255)
	@ColumnComment("无法确定")
    private String name;

    @TableField("description")
	@ColumnType(length=255)
	@ColumnComment("描述")
    private String description;
}
