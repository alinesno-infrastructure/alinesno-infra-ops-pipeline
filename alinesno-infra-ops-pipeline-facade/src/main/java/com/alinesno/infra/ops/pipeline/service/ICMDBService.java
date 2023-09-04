package com.alinesno.infra.ops.pipeline.service;

import com.alinesno.infra.ops.pipeline.entity.ServerEntity;

import java.util.List;

/**
 * 数据库管理服务接口
 * 该接口定义了与数据库管理相关的操作方法。
 *
 * @author  luoxiaodong
 * @version 1.0.0
 */
public interface ICMDBService {

    /**
     * distributeServerKeys 方法用于批量分发 SSH 密钥到各个服务器，用于初始化服务器管理。
     * 可以通过上传包含服务器密钥的文件进行批量操作。
     *
     * @param keyFilePath 密钥文件路径
     * @return 是否成功分发密钥的布尔值
     */
    boolean distributeServerKeys(String keyFilePath);

    /**
     * queryServerByTag 方法用于根据标签查询服务器。
     *
     * @param tag 标签
     * @return 符合标签条件的服务器列表
     */
    List<ServerEntity> queryServerByTag(String tag);
}
