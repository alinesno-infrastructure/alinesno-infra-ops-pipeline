package com.alinesno.infra.ops.pipeline.service;

import com.alinesno.infra.ops.pipeline.dto.TaskInfoDto;
import com.alinesno.infra.ops.pipeline.entity.JobEntity;
import com.alinesno.infra.ops.pipeline.entity.TransEntity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * IDistSchedulerService接口是分布式调度任务的服务接口。
 * 它定义了创建、停止、移除和列举任务的方法。
 */
public interface IDistSchedulerService {

    /**
     * 创建定时任务
     *
     * @param taskInfoDto 任务信息DTO
     * @param jobEntity   任务实体对象
     * @param listTrans   转换实体对象列表
     * @throws SQLException 如果发生SQL异常
     * @throws IOException  如果发生IO异常
     */
    void createCronJob(TaskInfoDto taskInfoDto, JobEntity jobEntity, List<TransEntity> listTrans) throws SQLException, IOException;

    /**
     * 创建定时任务
     *
     * @param jobId 任务ID
     * @throws SQLException 如果发生SQL异常
     * @throws IOException  如果发生IO异常
     */
    void createCronJob(Long jobId) throws SQLException, IOException;
}
