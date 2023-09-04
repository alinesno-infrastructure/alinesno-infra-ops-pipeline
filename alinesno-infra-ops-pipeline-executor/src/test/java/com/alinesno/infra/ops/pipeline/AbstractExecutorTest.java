package com.alinesno.infra.ops.pipeline;

import com.alinesno.infra.ops.pipeline.dto.ExecutorScriptDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AbstractExecutorTest {

    @Test
    @DisplayName("测试 createJdbcTemplate 方法")
    public void testCreateJdbcTemplate() {
        // 准备测试数据
        String jdbcUrl = "jdbc:mysql://localhost:3306/mydatabase";
        String driverClass = "com.mysql.jdbc.Driver";
        String username = "root";
        String password = "password";

        // 调用被测试方法
        JdbcTemplate jdbcTemplate = createTestExecutor().createJdbcTemplate(jdbcUrl, driverClass, username, password);

        // 验证结果
        DriverManagerDataSource dataSource = (DriverManagerDataSource) jdbcTemplate.getDataSource();
        assert dataSource != null;

        assertEquals(jdbcUrl, dataSource.getUrl());
        assertEquals(username, dataSource.getUsername());
        assertEquals(password, dataSource.getPassword());
    }

    @Test
    @DisplayName("测试 executeQuery 方法 - 查询成功")
    public void testExecuteQuerySuccess() {
        // 准备测试数据
        String querySql = "SELECT * FROM mytable";
        List<Map<String, Object>> expectedResult = createTestResult();

        // 创建测试用的 JdbcTemplate
        JdbcTemplate jdbcTemplate = createTestExecutor().createJdbcTemplate("", "", "", "");

        // 调用被测试方法
        List<Map<String, Object>> result = createTestExecutor().executeQuery(jdbcTemplate, querySql);

        // 验证结果
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("测试 executeQuery 方法 - 查询失败")
    public void testExecuteQueryFailure() {
        // 准备测试数据
        String querySql = "SELECT * FROM mytable";
        RuntimeException expectedException = new RuntimeException("执行 MySQL 查询失败");

        // 创建测试用的 JdbcTemplate
        JdbcTemplate jdbcTemplate = createFailingTestExecutor().createJdbcTemplate("", "", "", "");

        // 调用被测试方法，并捕获异常
        Throwable exception = assertThrows(Throwable.class, () -> createFailingTestExecutor().executeQuery(jdbcTemplate, querySql));

        // 验证异常
        assertEquals(expectedException.getClass(), exception.getClass());
        assertEquals(expectedException.getMessage(), exception.getMessage());
    }

    // 创建测试用的 AbstractExecutor 实例
    private AbstractExecutor createTestExecutor() {
        return new AbstractExecutor() {
            @Override
            public void run(ExecutorScriptDto executorScriptDto, Map<String, Object> contextMap) {
                // 不需要实现
            }
        };
    }

    // 创建执行查询失败的测试用的 AbstractExecutor 实例
    private AbstractExecutor createFailingTestExecutor() {
        return new AbstractExecutor() {
            @Override
            public void run(ExecutorScriptDto executorScriptDto, Map<String, Object> contextMap) {
                // 不需要实现
            }
        };
    }

    // 创建测试用的查询结果
    private List<Map<String, Object>> createTestResult() {
        List<Map<String, Object>> result = new ArrayList<>();

        Map<String, Object> row1 = new HashMap<>();
        row1.put("id", 1);
        row1.put("name", "John");
        result.add(row1);

        Map<String, Object> row2 = new HashMap<>();
        row2.put("id", 2);
        row2.put("name", "Jane");
        result.add(row2);

        return result;
    }
}
