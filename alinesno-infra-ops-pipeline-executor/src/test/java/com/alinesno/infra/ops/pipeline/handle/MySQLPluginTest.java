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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MySQLPluginTest {

    @Mock
    private ExecutorScriptDto executorScriptDto;

    @BeforeEach
    void setUp() {
        // 初始化 Mockito 注解
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void run_ShouldExecuteQueryAndPutResultInContextMap_WhenConfigurationIsValid() {
        // 准备
        MySQLPlugin mySQLPlugin = new MySQLPlugin();
        Map<String, Object> contextMap = new HashMap<>();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("jdbcUrl", "jdbc:mysql://localhost:3306/test");
        attributes.put("driverClass", "com.mysql.jdbc.Driver");
        attributes.put("username", "root");
        attributes.put("password", "password");
        attributes.put("querySql", "SELECT * FROM table");
        when(executorScriptDto.getAttributes()).thenReturn(AttributeUtils.convertMapToAttributes(attributes));

        // 设置模拟的查询结果
        List<Map<String, Object>> queryResult = createMockQueryResult();
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        when(mySQLPlugin.executeQuery(jdbcTemplate, "SELECT * FROM table")).thenReturn(queryResult);

        // 执行
        mySQLPlugin.run(executorScriptDto, contextMap);

        // 断言
        // 验证创建 JdbcTemplate 和执行查询 SQL 的方法是否被调用
        verify(mySQLPlugin).executeQuery(jdbcTemplate, "SELECT * FROM table");
        // 验证查询结果是否正确放入 contextMap
        assertEquals(queryResult, contextMap.get("mysqlResult"));
    }

    private List<Map<String, Object>> createMockQueryResult() {
        // 创建模拟的查询结果
        // 这里只是一个示例，实际情况下可以根据需要创建具体的查询结果
        // 使用 Mockito 的 mock 方法来创建 List 和 Map 的模拟对象
        List<Map<String, Object>> queryResult = mock(List.class);
        Map<String, Object> row1 = mock(Map.class);
        when(row1.get("column1")).thenReturn("value1");
        when(row1.get("column2")).thenReturn(123);
        Map<String, Object> row2 = mock(Map.class);
        when(row2.get("column1")).thenReturn("value2");
        when(row2.get("column2")).thenReturn(456);
        when(queryResult.size()).thenReturn(2);
        when(queryResult.get(0)).thenReturn(row1);
        when(queryResult.get(1)).thenReturn(row2);
        return queryResult;
    }
}
