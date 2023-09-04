package com.alinesno.infra.ops.pipeline.handle;

import com.alinesno.infra.ops.pipeline.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.pipeline.dto.ValueAttributeDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class GroovyPluginTest {

    @Test
    public void testRun() {
        // 准备测试数据
        ExecutorScriptDto executorScriptDto = new ExecutorScriptDto();
        executorScriptDto.setScriptContent("def multiply(int a, int b) { return a * b }");

        List<ValueAttributeDto> attrs = new ArrayList<>();
        attrs.add(new ValueAttributeDto("param1", "2"));
        attrs.add(new ValueAttributeDto("param2", "3"));

        executorScriptDto.setAttributes(attrs);

        // 创建 GroovyPlugin 实例
        GroovyPlugin groovyPlugin = new GroovyPlugin();

        try {
            // 执行 run 方法
            groovyPlugin.run(executorScriptDto, null);
        } catch (Exception e) {
            // 捕获异常并标记测试失败
            fail("Exception thrown: " + e.getMessage());
        }

        // 在这里可以添加更多的断言来验证预期结果
        // 例如，验证日志输出或执行结果

        // 这里只是一个示例，你可以根据你的需求添加更多的测试逻辑

        // 验证断言示例
//        assertEquals(6, result); // 假设脚本返回的结果是 6
    }
}
