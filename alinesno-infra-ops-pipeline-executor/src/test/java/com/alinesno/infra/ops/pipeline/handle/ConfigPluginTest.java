package com.alinesno.infra.ops.pipeline.handle;

import com.alinesno.infra.ops.pipeline.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.pipeline.utils.AttributeUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConfigPluginTest {

    @Mock
    private ExecutorScriptDto executorScriptDto;

    @BeforeEach
    void setUp() {
        // 初始化 Mockito 注解
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void run_ShouldReadConfigContentAndPutInContextMap_WhenConfigUrlIsValid() throws IOException {
        // 准备
        ConfigPlugin configPlugin = new ConfigPlugin();
        Map<String, Object> contextMap = new HashMap<>();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("config-url", "https://example.com/config.txt");
        when(executorScriptDto.getAttributes()).thenReturn(AttributeUtils.convertMapToAttributes(attributes));

        // 设置模拟的配置内容
        String configContent = "Sample config content";
        when(configPlugin.readConfigContent("https://example.com/config.txt")).thenReturn(configContent);

        // 执行
        configPlugin.run(executorScriptDto, contextMap);

        // 断言
        // 验证读取配置内容的方法是否被调用
        verify(configPlugin).readConfigContent("https://example.com/config.txt");
        // 验证配置内容是否正确放入 contextMap
        assertEquals(configContent, contextMap.get("configContent"));
    }

    @Test
    void run_ShouldHandleException_WhenConfigUrlIsInvalid() throws IOException {
        // 准备
        ConfigPlugin configPlugin = new ConfigPlugin();
        Map<String, Object> contextMap = new HashMap<>();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("config-url", "invalid-url");
        when(executorScriptDto.getAttributes()).thenReturn(AttributeUtils.convertMapToAttributes(attributes));

        // 设置模拟的读取配置内容方法抛出异常
        when(configPlugin.readConfigContent("invalid-url")).thenThrow(new IOException("Invalid URL"));

        // 执行
        configPlugin.run(executorScriptDto, contextMap);

        // 断言
        // 验证异常处理逻辑是否执行
    }
}
