package com.alinesno.infra.ops.pipeline.handle;

import com.alinesno.infra.ops.pipeline.AbstractExecutor;
import com.alinesno.infra.ops.pipeline.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.pipeline.utils.AttributeUtils;
import groovy.lang.GroovyShell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * GroovyPlugin类是一个Groovy插件执行器，继承自AbstractExecutor抽象类。
 * 它用于执行Groovy相关任务。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class GroovyPlugin extends AbstractExecutor {

    private static final Logger log = LoggerFactory.getLogger(GroovyPlugin.class);

    @Override
    public void run(ExecutorScriptDto executorScriptDto, Map<String, Object> contextMap) {
        // 获取执行器的脚本内容
        String script = executorScriptDto.getScriptContent();
        Map<String , Object> attr = AttributeUtils.convertAttributesToMap(executorScriptDto.getAttributes()) ;

        // 获取上下文中的参数
        Object param1 = attr.get("param1");
        Object param2 = attr.get("param2");

        // 执行Groovy脚本逻辑
        try {
            GroovyShell shell = new GroovyShell();

            // 设置脚本中可用的参数
            shell.setVariable("a", Integer.parseInt(param1+""));
            shell.setVariable("b", Integer.parseInt(param2+""));

            // 执行脚本
            Object result = shell.evaluate(script);

            // 输出结果日志
            log.info("Groovy script executed successfully. Result: {}", result);
        } catch (Exception e) {
            // 处理异常情况
            log.error("Error executing Groovy script: {}", e.getMessage(), e);
        }
    }
}
