package com.alinesno.infra.ops.pipeline.handle;

import com.alinesno.infra.ops.pipeline.AbstractExecutor;
import com.alinesno.infra.ops.pipeline.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.pipeline.utils.AttributeUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class ClickhousePlugin extends AbstractExecutor {


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

        // 创建 ClickHouse 的 JdbcTemplate
        JdbcTemplate jdbcTemplate = createJdbcTemplate(jdbcUrl, driverClass, username, password);

        // 执行 ClickHouse 的查询 SQL
        List<Map<String, Object>> queryResult = executeQuery(jdbcTemplate, querySql);

        // 将查询结果放入 contextMap
        contextMap.put("clickhouseResult", queryResult);
    }
}
