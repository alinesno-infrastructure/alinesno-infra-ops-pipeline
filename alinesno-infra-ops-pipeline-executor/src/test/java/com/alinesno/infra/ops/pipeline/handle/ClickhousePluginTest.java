package com.alinesno.infra.ops.pipeline.handle;

import com.alinesno.infra.ops.pipeline.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.pipeline.utils.AttributeUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

class ClickhousePluginTest {

    @Mock
    private ExecutorScriptDto executorScriptDto;

    @BeforeEach
    void setUp() {
        // 初始化 Mockito 注解
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void run_ShouldExecuteQueryAndPutResultInContextMap() {
        // 准备
        ClickhousePlugin clickhousePlugin = new ClickhousePlugin();
        Map<String, Object> contextMap = new HashMap<>();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("jdbcUrl", "jdbc:clickhouse://localhost:8123/my_database");
        attributes.put("driverClass", "ru.yandex.clickhouse.ClickHouseDriver");
        attributes.put("username", "my_username");
        attributes.put("password", "my_password");
        attributes.put("querySql", "SELECT * FROM my_table");
        when(executorScriptDto.getAttributes()).thenReturn(AttributeUtils.convertMapToAttributes(attributes));

        // 创建模拟的 JdbcTemplate
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);

        // 模拟查询结果
        List<Map<String, Object>> queryResult = List.of(
                Map.of("id", 1, "name", "John"),
                Map.of("id", 2, "name", "Jane")
        );
        when(jdbcTemplate.queryForList("SELECT * FROM my_table")).thenReturn(queryResult);

        // 执行
        clickhousePlugin.run(executorScriptDto, contextMap);

        // 断言
        // 验证 JdbcTemplate 的 queryForList 方法是否以正确的参数被调用
        verify(jdbcTemplate).queryForList("SELECT * FROM my_table");
        // 验证查询结果是否被放入 contextMap
        assertSame(queryResult, contextMap.get("clickhouseResult"));
    }
}
