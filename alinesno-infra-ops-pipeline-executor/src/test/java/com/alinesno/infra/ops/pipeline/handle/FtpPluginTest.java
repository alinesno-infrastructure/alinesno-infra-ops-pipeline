package com.alinesno.infra.ops.pipeline.handle;

import com.alinesno.infra.ops.pipeline.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.pipeline.utils.AttributeUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.HashMap;
import java.util.Map;

import static com.alinesno.infra.ops.pipeline.handle.FtpPlugin.*;

@DisplayName("FtpPlugin 单元测试")
class FtpPluginTest {

    @ParameterizedTest
    @DisplayName("测试 run 方法")
    @CsvFileSource(files = "/home/WUYING_luoandong_1788404793/Desktop/run_ShouldUploadFileToFTP.csv" , numLinesToSkip = 1)
    void run_ShouldUploadFileToFTP(String host , String username , String password , String path , String remotePath) {

        if(remotePath == null){
            remotePath = "" ;
        }

        // 准备
        Map<String, Object> contextMap = new HashMap<>();
        contextMap.put(PROP_HOST, host);
        contextMap.put(PROP_USERNAME, username);
        contextMap.put(PROP_PASSWORD, password);
        contextMap.put(PROP_LOCAL_FILE, path);
        contextMap.put(PROP_REMOTE_PATH, remotePath);

        FtpPlugin ftpPlugin = new FtpPlugin();

        ExecutorScriptDto dto = new ExecutorScriptDto();
        dto.setAttributes(AttributeUtils.convertMapToAttributes(contextMap));

        // 执行
        ftpPlugin.run(dto, contextMap);

        // 断言
        // 在这里添加适当的断言来验证文件是否成功上传到 FTP 服务器
        // 可以使用 FTP 客户端库或模拟 FTP 服务器进行断言
        // 例如：
        // assertTrue(ftpClient.fileExists("/path/to/remote/file.txt"));
    }
}
