package com.alinesno.infra.ops.pipeline.service.impl;

import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.ops.pipeline.entity.ServerKeyEntity;
import com.alinesno.infra.ops.pipeline.mapper.ServerKeyMapper;
import com.alinesno.infra.ops.pipeline.service.IServerKeyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 服务器密钥Service业务层处理
 * 
 * @author luoxiaodong
 * @version  1.0.0
 */
@Service
public class ServerKeyServiceImpl extends IBaseServiceImpl<ServerKeyEntity, ServerKeyMapper> implements IServerKeyService {
    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(ServerKeyServiceImpl.class);
}
