package com.alinesno.infra.ops.pipeline.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.pipeline.entity.ServerEntity;
import com.alinesno.infra.ops.pipeline.mapper.ServerMapper;
import com.alinesno.infra.ops.pipeline.service.IServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 服务器Service业务层处理
 *
 * @author luoxiaodong
 * @version  1.0.0
 */
@Service
public class ServerServiceImpl extends IBaseServiceImpl<ServerEntity, ServerMapper> implements IServerService {
    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(ServerServiceImpl.class);
}
