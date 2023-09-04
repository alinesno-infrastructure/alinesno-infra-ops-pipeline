package com.alinesno.infra.ops.pipeline.handle;

import com.alinesno.infra.ops.pipeline.AbstractExecutor;
import com.alinesno.infra.ops.pipeline.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.pipeline.utils.AttributeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * 文件下载插件执行器
 *
 * 该类是一个文件下载插件的执行器，用于执行文件下载任务。
 * 继承自抽象类 AbstractExecutor，并实现其中的 run 方法。
 * 在 run 方法中，执行下载逻辑，并将下载的文件地址放入 contextMap 中。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class DownloadPlugin extends AbstractExecutor {

    private static final Logger log = LoggerFactory.getLogger(DownloadPlugin.class);

    private static final String PROP_FILE_URL = "file-url" ;

    @Override
    public void run(ExecutorScriptDto executorScriptDto, Map<String, Object> contextMap) {

        Map<String , Object> attrs = AttributeUtils.convertAttributesToMap(executorScriptDto.getAttributes()) ;
        String fileUrl = (String) attrs.get(PROP_FILE_URL);

        // 执行下载逻辑
        try {
            // 这里是你的下载逻辑实现
            // 可以使用第三方库或自定义代码来执行下载操作
            // 根据你的需求，可能需要使用参数和脚本内容来配置下载任务

            // 示例：下载文件的逻辑
            String destinationPath = "/tmp/destination"; // 假设指定了文件的目标路径

            // 调用下载方法
            boolean downloadResult = downloadFile(fileUrl, destinationPath);

            if (downloadResult) {
                // 下载成功的处理逻辑
                System.out.println("文件下载成功");

                // 将下载的文件地址放入 contextMap
                Path filePath = Paths.get(destinationPath);
                contextMap.put("downloadedFilePath", filePath);

                // 可以输出日志或执行其他操作
            } else {
                // 下载失败的处理逻辑
                log.debug("文件下载失败");
                // 可以输出日志或执行其他操作
            }
        } catch (Exception e) {
            // 处理异常情况
            log.debug("文件下载异常: " + e.getMessage());
            // 可以输出日志或执行其他操作
        }
    }

    // 示例：下载文件的方法
    boolean downloadFile(String fileUrl, String destinationPath) throws Exception {
        // 这里是下载文件的具体实现逻辑
        // 可以使用第三方库或自定义代码来执行下载操作

        URL url = new URL(fileUrl);
        try (InputStream inputStream = url.openStream();
             FileOutputStream outputStream = new FileOutputStream(destinationPath)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        // 返回下载结果，这里假设下载成功
        return true;
    }
}
