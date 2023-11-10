package com.alinesno.infra.ops.pipeline.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 *
 * 数据表：qrtz_locks
 * 表备注：
 *
 * @TableName 表名注解，用于指定数据库表名
 * @TableField 字段注解，用于指定数据库字段名
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("qrtz_locks")
@Data
public class QrtzLocksEntity extends InfraBaseEntity {

    /**
     * schedName
     */
    @TableField("sched_name")
    @ColumnType(length=255)
    @ColumnComment("schedName")
    private String schedName;

    /**
     * lockName
     */
    @TableField("lock_name")
    @ColumnType(length=255)
    @ColumnComment("lockName")
    private String lockName;
}
