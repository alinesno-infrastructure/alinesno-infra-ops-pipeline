package com.alinesno.infra.ops.pipeline.handle;

import com.alinesno.infra.ops.pipeline.AbstractExecutor;
import com.alinesno.infra.ops.pipeline.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.pipeline.utils.AttributeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * MySQLPlugin类是一个MySQL插件执行器，继承自AbstractExecutor抽象类。
 * 它用于执行MySQL相关任务。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class MySQLPlugin extends AbstractExecutor {

    private static final Logger log = LoggerFactory.getLogger(MySQLPlugin.class);

    /**
     * 执行MySQL相关任务的具体逻辑。
     *
     * @param executorScriptDto 包含执行器脚本的DTO对象
     * @param contextMap        上下文映射，用于存储执行结果
     */
    @Override
    public void run(ExecutorScriptDto executorScriptDto, Map<String, Object> contextMap) {

        // 获取配置属性
        Map<String , Object> attrs = AttributeUtils.convertAttributesToMap(executorScriptDto.getAttributes()) ;

        String jdbcUrl = (String) attrs.get(PROP_JDBC_URL);
        String driverClass = (String) attrs.get(PROP_DRIVER_CLASS);
        String username = (String) attrs.get(PROP_USERNAME);
        String password = (String) attrs.get(PROP_PASSWORD);
        String querySql = (String) attrs.get(PROP_QUERY_SQL);

        String serverId = (String) attrs.get(PROP_SERVER_ID);
        findHostKey(serverId , jdbcUrl , username , password);

        // 创建 MySQL 的 JdbcTemplate
        JdbcTemplate jdbcTemplate = createJdbcTemplate(jdbcUrl, driverClass, username, password);

        // 执行 MySQL 的查询 SQL
        List<Map<String, Object>> queryResult = executeQuery(jdbcTemplate, querySql);

        // 将查询结果放入 contextMap
        contextMap.put("mysqlResult", queryResult);
    }


}
