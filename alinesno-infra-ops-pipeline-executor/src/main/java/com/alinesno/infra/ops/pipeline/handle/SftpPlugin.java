package com.alinesno.infra.ops.pipeline.handle;

import com.alinesno.infra.ops.pipeline.AbstractExecutor;
import com.alinesno.infra.ops.pipeline.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.pipeline.exception.ExecutorServiceRuntimeException;
import com.alinesno.infra.ops.pipeline.utils.AttributeUtils;
import com.alinesno.infra.ops.pipeline.utils.SftpClient;
import com.jcraft.jsch.SftpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;

/**
 * SftpPlugin类是一个SFTP插件执行器，继承自AbstractExecutor抽象类。
 * 它用于执行SFTP相关任务。
 */
public class SftpPlugin extends AbstractExecutor {

    private static final Logger log = LoggerFactory.getLogger(SftpPlugin.class);

    private static final String PROP_SERVER_ID = "server-id";

    private static final String PROP_HOST = "host";
    private static final String PROP_USERNAME = "username";
    private static final String PROP_PASSWORD = "password";
    private static final String PROP_PATH = "path";
    private static final String PROP_UPLOAD_FILE_PATH = "upload-file-path";


    /**
     * 在此方法中实现SFTP相关任务的具体逻辑。
     *
     * @param executorScriptDto SFTP插件执行器的脚本DTO
     * @param contextMap        上下文参数Map，用于传递执行所需的上下文信息
     */
    @Override
    public void run(ExecutorScriptDto executorScriptDto, Map<String, Object> contextMap) {
        // 获取配置属性
        Map<String , Object> attrs = AttributeUtils.convertAttributesToMap(executorScriptDto.getAttributes()) ;

        String serverId = (String) attrs.get(PROP_SERVER_ID);

        String host = (String) attrs.get(PROP_HOST);
        String username = (String) attrs.get(PROP_USERNAME);
        String password = (String) attrs.get(PROP_PASSWORD);
        String path = (String) attrs.get(PROP_PATH);
        String uploadFilePath = (String) attrs.get(PROP_UPLOAD_FILE_PATH);


        findHostKey(serverId , host , username , password);

//        String uploadFilePath = (String) contextMap.get("uploadFilePath");

        // 执行 SFTP 相关任务的具体逻辑
        try {
            // 创建 SFTP 连接并进行操作
            SftpClient sftpClient = new SftpClient(host, username, password);
            sftpClient.connect();

            // 判断远程目录是否存在
            try {
                sftpClient.cd(path);
                log.debug("Remote directory already exists: " + path);
            } catch (SftpException e) {
                // 远程目录不存在，创建目录
                sftpClient.mkdir(path);
                log.debug("Remote directory created: " + path);
            }

            // 在指定路径下上传或下载文件
            // 例如，上传文件到远程服务器
            File uf = new File(uploadFilePath) ;
            String uploadPath = path + "/" + uf.getName() ;

            log.debug("upload path = {}" , uploadPath);
            sftpClient.uploadFile(uploadFilePath, uploadPath);

            // 关闭 SFTP 连接
            sftpClient.disconnect();
        } catch (Exception e) {
            log.error("SFTP任务执行失败: {}", e.toString());
            throw new ExecutorServiceRuntimeException(e.getMessage()) ;
        }
    }
}
