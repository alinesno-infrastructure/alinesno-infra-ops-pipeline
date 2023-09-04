package com.alinesno.infra.ops.pipeline.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.pipeline.entity.JobEntity;
import com.alinesno.infra.ops.pipeline.mapper.JobMapper;
import com.alinesno.infra.ops.pipeline.service.IJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @version 1.0.0
 * @autor luoxiaodong
 */
@Service
public class JobServiceImpl extends IBaseServiceImpl<JobEntity, JobMapper> implements IJobService {
    // 日志记录
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(JobServiceImpl.class);
}