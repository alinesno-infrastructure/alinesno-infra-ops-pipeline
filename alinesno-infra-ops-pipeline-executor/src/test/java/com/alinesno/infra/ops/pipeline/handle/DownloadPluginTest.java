package com.alinesno.infra.ops.pipeline.handle;

import com.alinesno.infra.ops.pipeline.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.pipeline.utils.AttributeUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DownloadPluginTest {

    @Mock
    private ExecutorScriptDto executorScriptDto;

    @BeforeEach
    void setUp() {
        // 初始化 Mockito 注解
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void run_ShouldDownloadFileAndPutPathInContextMap_WhenDownloadSucceeds() throws Exception {
        // 准备
        DownloadPlugin downloadPlugin = new DownloadPlugin();
        Map<String, Object> contextMap = new HashMap<>();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("file-url", "https://example.com/file.txt");
        when(executorScriptDto.getAttributes()).thenReturn(AttributeUtils.convertMapToAttributes(attributes));

        // 创建模拟的 URL 和文件流
        URL url = mock(URL.class);
        InputStream inputStream = mock(InputStream.class);
        FileOutputStream outputStream = mock(FileOutputStream.class);

        // 设置模拟的下载结果
        when(url.openStream()).thenReturn(inputStream);

        // 模拟下载的文件路径
        String destinationPath = "/path/to/destination";
        Path filePath = Paths.get(destinationPath);

        // 执行
        boolean downloadResult = downloadPlugin.downloadFile("https://example.com/file.txt", destinationPath);
        downloadPlugin.run(executorScriptDto, contextMap);

        // 断言
        // 验证下载方法是否以正确的参数被调用
        verify(url).openStream();
        verify(outputStream).write(any(byte[].class), anyInt(), anyInt());
        // 验证下载结果和文件路径是否正确
        assertTrue(downloadResult);
        assertEquals(filePath, contextMap.get("downloadedFilePath"));
    }

    @Test
    void run_ShouldHandleException_WhenDownloadThrowsException() throws Exception {
        // 准备
        DownloadPlugin downloadPlugin = new DownloadPlugin();
        Map<String, Object> contextMap = new HashMap<>();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("file-url", "https://example.com/file.txt");
        when(executorScriptDto.getAttributes()).thenReturn(AttributeUtils.convertMapToAttributes(attributes));

        // 创建模拟的 URL 和文件流
        URL url = mock(URL.class);

        // 设置模拟的下载结果
        when(url.openStream()).thenThrow(new Exception("Download failed"));

        // 执行
        downloadPlugin.run(executorScriptDto, contextMap);

        // 断言
        // 验证异常处理逻辑是否执行
        assertFalse(contextMap.containsKey("downloadedFilePath"));
    }
}
