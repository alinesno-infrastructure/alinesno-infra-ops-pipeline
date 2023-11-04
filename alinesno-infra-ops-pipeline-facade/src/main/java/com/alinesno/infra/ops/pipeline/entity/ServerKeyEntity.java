package com.alinesno.infra.ops.pipeline.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 服务器密钥实体类
 */
@TableName("server_key")
@Data
public class ServerKeyEntity extends InfraBaseEntity {

    @TableField("server_id")
	@ColumnType(length=10)
	@ColumnComment("服务器ID")
    private Long serverId;

    @TableField("key")
	@ColumnType(length=255)
	@ColumnComment("键")
    private String key;

    @TableField("type")
	@ColumnType(length=255)
	@ColumnComment("密钥类型")
    private String type ; // 密钥类型(database/server/webhook/git/normal)

    @TableField("host_path")
	@ColumnType(length=255)
	@ColumnComment("String")
    private String hostPath ;

    @TableField("username")
	@ColumnType(length=255)
	@ColumnComment("用户名")
    private String username ;

    @TableField("password")
	@ColumnType(length=255)
	@ColumnComment("密码")
    private String password ;
}
