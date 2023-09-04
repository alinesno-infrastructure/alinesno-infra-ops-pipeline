package com.alinesno.infra.ops.pipeline.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.pipeline.entity.TransEntity;
import com.alinesno.infra.ops.pipeline.mapper.TransMapper;
import com.alinesno.infra.ops.pipeline.service.ITransService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 转换Service业务层处理
 *
 * @version 1.0.0
 * @author luoxiaodong
 */
@Service
public class TransServiceImpl extends IBaseServiceImpl<TransEntity, TransMapper> implements ITransService {
    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(TransServiceImpl.class);
}