package com.alinesno.infra.ops.pipeline.handle;

import com.alinesno.infra.ops.pipeline.AbstractExecutor;
import com.alinesno.infra.ops.pipeline.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.pipeline.utils.AttributeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * AlarmPlugin 类是一个告警插件执行器，继承自 AbstractExecutor 抽象类。
 * 它用于执行告警相关任务。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class AlarmPlugin extends AbstractExecutor {

    private static final Logger log = LoggerFactory.getLogger(AlarmPlugin.class);

    private static final String PROP_TYPE = "type";       // ding-talk/wechat/http
    private static final String PROP_WEBHOOK = "webhook";
    private static final String PROP_SECRET = "secret";

    @Override
    public void run(ExecutorScriptDto executorScriptDto, Map<String, Object> contextMap) {
        // 获取配置属性
        Map<String , Object> attrs = AttributeUtils.convertAttributesToMap(executorScriptDto.getAttributes()) ;
        String type = (String) attrs.get(PROP_TYPE);
        String webhook = (String) attrs.get(PROP_WEBHOOK);
        String secret = (String) attrs.get(PROP_SECRET);

        String serverId = (String) attrs.get(PROP_SERVER_ID);
        findHostKey(serverId , webhook, null , secret);

        // 根据配置的通知渠道类型选择性发送通知
        if ("ding-talk".equals(type)) {
            sendDingTalkNotification(webhook, secret, contextMap);
        } else if ("wechat".equals(type)) {
            sendWeChatNotification(webhook, contextMap);
        } else if ("http".equals(type)) {
            sendHttpNotification(webhook, contextMap);
        } else {
            log.warn("未知的通知渠道类型: {}", type);
        }
    }

    // 发送钉钉通知的方法
    public void sendDingTalkNotification(String webhook, String secret, Map<String, Object> contextMap) {
        // 构造通知内容
        String message = "这是一条钉钉通知";

        // 在这里实现发送钉钉通知的具体逻辑
        // 使用 webhook 和 secret 发送通知
        // 可以使用第三方库或自己实现 HTTP 请求
        // 可以使用 contextMap 中的数据来构造通知内容
        log.info("发送钉钉通知");

        // 示例：使用 Apache HttpClient 发送 HTTP POST 请求
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(webhook);

            // 构造请求内容
            StringEntity stringEntity = new StringEntity(message, ContentType.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);

            // 发送请求并获取响应
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            String responseString = EntityUtils.toString(responseEntity);

            // 处理响应
            log.info("钉钉通知响应：{}", responseString);
        } catch (IOException e) {
            log.error("发送钉钉通知失败", e);
        }
    }

    // 发送微信通知的方法
    public void sendWeChatNotification(String webhook, Map<String, Object> contextMap) {
        // 构造通知内容
        String message = "这是一条微信通知";

        // 在这里实现发送微信通知的具体逻辑
        // 使用 webhook 发送通知
        // 可以使用第三方库或自己实现 HTTP 请求
        // 可以使用 contextMap 中的数据来构造通知内容
        log.info("发送微信通知");

        // 示例：使用 Apache HttpClient 发送 HTTP POST 请求
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(webhook);

            // 构造请求内容
            StringEntity stringEntity = new StringEntity(message, ContentType.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);

            // 发送请求并获取响应
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            String responseString = EntityUtils.toString(responseEntity);

            // 处理响应
            log.info("微信通知响应：{}", responseString);
        } catch (IOException e) {
            log.error("发送微信通知失败", e);
        }
    }

    // 发送 HTTP 通知的方法
    public void sendHttpNotification(String webhook, Map<String, Object> contextMap) {
        // 构造通知内容
        String message = "这是一条 HTTP 通知";

        // 在这里实现发送 HTTP 通知的具体逻辑
        // 使用 webhook 发送通知
        // 可以使用第三方库或自己实现 HTTP 请求
        // 可以使用 contextMap 中的数据来构造通知内容
        log.info("发送 HTTP 通知");

        // 示例：使用 Apache HttpClient 发送 HTTP POST 请求
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(webhook);

            // 构造请求内容
            StringEntity stringEntity = new StringEntity(message, ContentType.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);

            // 发送请求并获取响应
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            String responseString = EntityUtils.toString(responseEntity);

            // 处理响应
            log.info("HTTP 通知响应：{}", responseString);
        } catch (IOException e) {
            log.error("发送 HTTP 通知失败", e);
        }
    }
}