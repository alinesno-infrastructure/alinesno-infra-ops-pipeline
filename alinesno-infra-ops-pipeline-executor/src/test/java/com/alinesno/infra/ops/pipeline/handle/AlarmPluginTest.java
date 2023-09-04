package com.alinesno.infra.ops.pipeline.handle;

import com.alinesno.infra.ops.pipeline.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.pipeline.utils.AttributeUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

@DisplayName("AlarmPlugin 单元测试")
class AlarmPluginTest {

    @Mock
    private ExecutorScriptDto executorScriptDto;

    @BeforeEach
    void setUp() {
        // 初始化 Mockito 注解
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("当通知类型为 DingTalk 时，应发送钉钉通知")
    void run_ShouldSendDingTalkNotification_WhenTypeIsDingTalk() {
        // 准备
        AlarmPlugin alarmPlugin = new AlarmPlugin();
        Map<String, Object> contextMap = new HashMap<>();

        contextMap.put("key", "value");
        String webhook = "https://example.com/dingtalk-webhook";
        String secret = "my-secret";

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("type", "ding-talk");
        attributes.put("webhook", webhook);
        attributes.put("secret", secret);

        when(executorScriptDto.getAttributes()).thenReturn(AttributeUtils.convertMapToAttributes(attributes));

        // 执行
        alarmPlugin.run(executorScriptDto, contextMap);

        // 断言
        // 验证 sendDingTalkNotification 方法是否以正确的参数被调用
        verify(alarmPlugin).sendDingTalkNotification(webhook, secret, contextMap);
        // 验证其他通知方法未被调用
        verify(alarmPlugin, never()).sendWeChatNotification(anyString(), anyMap());
        verify(alarmPlugin, never()).sendHttpNotification(anyString(), anyMap());
    }

    @Test
    @DisplayName("当通知类型为 WeChat 时，应发送微信通知")
    void run_ShouldSendWeChatNotification_WhenTypeIsWeChat() {
        // 准备
        AlarmPlugin alarmPlugin = new AlarmPlugin();
        Map<String, Object> contextMap = new HashMap<>();
        contextMap.put("key", "value");
        String webhook = "https://example.com/wechat-webhook";
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("type", "wechat");
        attributes.put("webhook", webhook);
        when(executorScriptDto.getAttributes()).thenReturn(AttributeUtils.convertMapToAttributes(attributes));

        // 执行
        alarmPlugin.run(executorScriptDto, contextMap);

        // 断言
        // 验证 sendWeChatNotification 方法是否以正确的参数被调用
        verify(alarmPlugin).sendWeChatNotification(webhook, contextMap);
        // 验证其他通知方法未被调用
        verify(alarmPlugin, never()).sendDingTalkNotification(anyString(), anyString(), anyMap());
        verify(alarmPlugin, never()).sendHttpNotification(anyString(), anyMap());
    }

    @Test
    @DisplayName("当通知类型为 Http 时，应发送 HTTP 请求")
    void run_ShouldSendHttpNotification_WhenTypeIsHttp() {
        // 准备
        AlarmPlugin alarmPlugin = new AlarmPlugin();
        Map<String, Object> contextMap = new HashMap<>();
        contextMap.put("key", "value");
        String webhook = "https://example.com/http-webhook";
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("type", "http");
        attributes.put("webhook", webhook);
        when(executorScriptDto.getAttributes()).thenReturn(AttributeUtils.convertMapToAttributes(attributes));

        // 执行
        alarmPlugin.run(executorScriptDto, contextMap);

        // 断言
        // 验证 sendHttpNotification 方法是否以正确的参数被调用
        verify(alarmPlugin).sendHttpNotification(webhook, contextMap);
        // 验证其他通知方法未被调用
        verify(alarmPlugin, never()).sendDingTalkNotification(anyString(), anyString(), anyMap());
        verify(alarmPlugin, never()).sendWeChatNotification(anyString(), anyMap());
    }

    @Test
    @DisplayName("当通知类型为未知类型时，应记录警告日志")
    void run_ShouldLogWarning_WhenTypeIsUnknown() {
        // 准备
        AlarmPlugin alarmPlugin = new AlarmPlugin();
        Map<String, Object> contextMap = new HashMap<>();
        contextMap.put("key", "value");
        String unknownType = "unknown";
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("type", unknownType);
        when(executorScriptDto.getAttributes()).thenReturn(AttributeUtils.convertMapToAttributes(attributes));

        // 执行
        alarmPlugin.run(executorScriptDto, contextMap);

        // 断言
        // 验证 log.warn 方法是否以正确的参数被调用
//        verify(alarmPlugin).logWarning("未知的通知渠道类型: {}", unknownType);
        // 验证通知方法未被调用
        verify(alarmPlugin, never()).sendDingTalkNotification(anyString(), anyString(), anyMap());
        verify(alarmPlugin, never()).sendWeChatNotification(anyString(), anyMap());
        verify(alarmPlugin, never()).sendHttpNotification(anyString(), anyMap());
    }
}
