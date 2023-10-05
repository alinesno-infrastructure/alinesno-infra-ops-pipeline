package com.alinesno.infra.ops.pipeline.handle;

import com.alinesno.infra.ops.pipeline.AbstractExecutor;
import com.alinesno.infra.ops.pipeline.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.pipeline.utils.AttributeUtils;
import com.alinesno.infra.ops.pipeline.utils.FtpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;

/**
 * FtpPlugin类是一个FTP插件执行器，继承自AbstractExecutor抽象类。
 * 它用于执行FTP相关任务，包括文件上传等操作。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class FtpPlugin extends AbstractExecutor {

    private static final Logger log = LoggerFactory.getLogger(FtpPlugin.class);

    protected static final String PROP_LOCAL_FILE = "local-file";
    protected static final String PROP_REMOTE_PATH = "remote-path";

    /**
     * 重写父类的run方法，执行FTP文件上传任务。
     *
     * @param executorScriptDto 执行器脚本DTO对象
     * @param contextMap        上下文参数映射表，包含FTP连接所需的参数
     */
    @Override
    public void run(ExecutorScriptDto executorScriptDto, Map<String, Object> contextMap) {

        Map<String , Object> attrs = AttributeUtils.convertAttributesToMap(executorScriptDto.getAttributes()) ;

        String serverId = (String) attrs.get(PROP_SERVER_ID);

        String host = (String) attrs.get(PROP_HOST);
        String username = (String) attrs.get(PROP_USERNAME);
        String password = (String) attrs.get(PROP_PASSWORD);
        String localFile  = (String) attrs.get(PROP_LOCAL_FILE);
        String remotePath = (String) attrs.get(PROP_REMOTE_PATH) ;

        findHostKey(serverId , host , username , password);

        FtpClientUtil ftpClient = new FtpClientUtil(host , 21 , username , password) ;

        File file = new File(localFile) ;
        // 提高操作系统的兼容性，直接用“/”而不使用File.separator
        String remoteFileName = remotePath + "/" + file.getName();

        // TODO 若文件路径不存在则创建
        String remoteDirectory = remoteFileName.substring(0, remoteFileName.lastIndexOf("/"));
        ftpClient.mkdir(remoteDirectory);

        ftpClient.upload(remoteFileName, localFile);

    }
}
