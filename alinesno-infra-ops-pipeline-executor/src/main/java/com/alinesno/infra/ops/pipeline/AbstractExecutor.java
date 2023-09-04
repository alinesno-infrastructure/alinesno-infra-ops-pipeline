package com.alinesno.infra.ops.pipeline;

import com.alinesno.infra.common.core.context.SpringContext;
import com.alinesno.infra.ops.pipeline.command.runner.CmdExecutor;
import com.alinesno.infra.ops.pipeline.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.pipeline.entity.ServerKeyEntity;
import com.alinesno.infra.ops.pipeline.service.IServerKeyService;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;
import java.util.Map;

/**
 * 任务执行器抽象类
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public abstract class AbstractExecutor {

    public static final String PROP_SERVER_ID = "server-id";
    public static final String PROP_HOST = "host";
    public static final String PROP_USERNAME = "username";
    public static final String PROP_PASSWORD = "password";

    // JDBC
    public static final String PROP_JDBC_URL = "jdbcUrl";
    public static final String PROP_DRIVER_CLASS = "driverClass";
    public static final String PROP_QUERY_SQL = "querySql";

    /**
     * 执行任务
     *
     * @param executorScriptDto 执行器脚本数据传输对象
     * @param contextMap
     */
    public abstract void run(ExecutorScriptDto executorScriptDto, Map<String, Object> contextMap);

    /**
     * 创建的 JdbcTemplate。
     *
     * @param jdbcUrl     JDBC URL
     * @param driverClass JDBC驱动类名
     * @param username    用户名
     * @param password    密码
     * @return JdbcTemplate对象
     */
    protected JdbcTemplate createJdbcTemplate(String jdbcUrl, String driverClass, String username, String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(jdbcUrl);
        dataSource.setDriverClassName(driverClass);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return new JdbcTemplate(dataSource);
    }

    /**
     * 执行查询。
     *
     * @param jdbcTemplate JdbcTemplate对象
     * @param querySql     查询SQL语句
     * @return 查询结果列表
     * @throws RuntimeException 如果执行查询失败
     */
    public List<Map<String, Object>> executeQuery(JdbcTemplate jdbcTemplate, String querySql) {
        try {
            return jdbcTemplate.queryForList(querySql);
        } catch (Exception e) {
            throw new RuntimeException("执行 MySQL 查询失败", e);
        }
    }

    protected void findHostKey(String serverId, String host , String username , String password){

        if(StringUtils.isEmpty(host) || StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            IServerKeyService serverKeyService = SpringContext.getBean(IServerKeyService.class) ;
            ServerKeyEntity serverEntity = serverKeyService.getById(serverId) ;

            host = serverEntity.getHostPath() ;
            username = serverEntity.getUsername() ;
            password = serverEntity.getPassword() ;
        }
    }

    public void setCmdExecutor(CmdExecutor cmdExecutorMock) {

    }
}
