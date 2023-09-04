package com.alinesno.infra.ops.pipeline.service;

import com.alinesno.infra.ops.pipeline.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.pipeline.dto.TaskInfoDto;
import com.alinesno.infra.ops.pipeline.entity.JobEntity;

import java.util.Map;

/**
 * IExecutorScriptService接口是运行脚本的服务接口。
 * 它定义了运行脚本和分析任务信息的方法。
 */
public interface IExecutorScriptService {

    /**
     * 运行脚本
     *
     * @param executorScriptDto 执行器脚本DTO
     * @param contextMap 运行服务环境上下文
     */
    void run(ExecutorScriptDto executorScriptDto, Map<String, Object> contextMap);

    /**
     * 分析任务信息
     *
     * @param executorScriptDto 执行器脚本DTO
     * @return 任务实体对象
     */
    JobEntity analyzeTaskInfo(TaskInfoDto executorScriptDto);

}
