package com.alinesno.infra.ops.pipeline.handle;

import com.alinesno.infra.ops.pipeline.AbstractExecutor;
import com.alinesno.infra.ops.pipeline.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.pipeline.utils.AttributeUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
/**
 * ConfigPlugin 是一个配置插件执行器，继承自 AbstractExecutor 抽象类。
 * 它用于执行配置任务，读取指定 URL 的配置内容，并将其放入 contextMap 中。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class ConfigPlugin extends AbstractExecutor {

    private static final Logger log = LoggerFactory.getLogger(ConfigPlugin.class);
    private static final String PROP_CONFIG_URL = "config-url";

    @Override
    public void run(ExecutorScriptDto executorScriptDto, Map<String, Object> contextMap) {

        // 获取配置URL
        Map<String , Object> attrs = AttributeUtils.convertAttributesToMap(executorScriptDto.getAttributes()) ;
        String configUrl = (String) attrs.get(PROP_CONFIG_URL);

        try {
            // 读取配置内容
            String configContent = readConfigContent(configUrl);

            // 将配置内容放入contextMap
            contextMap.put("configContent", configContent);

            // 输出日志或执行其他操作
            log.info("配置内容已放入contextMap");
        } catch (IOException e) {
            // 处理异常情况
            log.error("读取配置内容失败: {}", e.getMessage());
        }
    }

    // 读取配置内容的方法
    String readConfigContent(String configUrl) throws IOException {
        // 这里是读取配置内容的具体实现逻辑
        // 使用 Apache Commons 的 IOUtils 类的 toString 方法来读取 URL 的内容

        URL url = new URL(configUrl);
        return IOUtils.toString(url, StandardCharsets.UTF_8);
    }
}
