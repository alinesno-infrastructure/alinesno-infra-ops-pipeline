package com.alinesno.infra.ops.pipeline.handle;

import com.alinesno.infra.ops.pipeline.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.pipeline.utils.AttributeUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SftpPluginTest {

    @ParameterizedTest
    @CsvFileSource(files = "/Users/luodong/test-data/case/testSftpRun.csv" , numLinesToSkip=1)
    @DisplayName("测试 run 方法 - 执行 SFTP 相关任务")
    public void testRun(String host, String username, String password, String path , String uploadFilePath) throws Exception {
        // 创建 SftpPlugin 实例
        SftpPlugin sftpPlugin = new SftpPlugin();

        // 设置上下文参数
        Map<String, Object> contextMap = new HashMap<>();
        contextMap.put("uploadFilePath", uploadFilePath);

        path += (File.separator + UUID.randomUUID()) ;

        Map<String, Object> attributeMap = new HashMap<>();
        attributeMap.put("host", host);
        attributeMap.put("username", username);
        attributeMap.put("password", password);
        attributeMap.put("path", path);

        // 创建 ExecutorScriptDto 实例
        ExecutorScriptDto executorScriptDto = new ExecutorScriptDto();
        executorScriptDto.setAttributes(AttributeUtils.convertMapToAttributes(attributeMap));

        // 调用 SftpPlugin 的 run 方法
        sftpPlugin.run(executorScriptDto, contextMap);
    }
}
