package com.alinesno.infra.ops.pipeline;

import com.alinesno.infra.ops.pipeline.enums.JobTypeEnums;
import com.alinesno.infra.ops.pipeline.handle.*;

import java.util.HashMap;
import java.util.Map;

/**
 * ScriptExecutorContext类是一个脚本容器类，用于管理不同类型的脚本执行器。
 */
public class ScriptExecutorContext {

    // 使用Map来存储不同类型的脚本执行器
    private static final Map<String, AbstractExecutor> mapPlugin = new HashMap<>();

    static {
        // 将ShellPlugin注册到脚本容器中
        mapPlugin.put(JobTypeEnums.SHELL.getCode(), new ShellPlugin());
        mapPlugin.put(JobTypeEnums.REMOTE_SHELL.getCode(), new RemoteShellPlugin());
        mapPlugin.put(JobTypeEnums.ALERT.getCode(), new AlarmPlugin());
        mapPlugin.put(JobTypeEnums.ANSIBLE.getCode(), new AnsiblePlugin());
        mapPlugin.put(JobTypeEnums.CLICKHOUSE.getCode(), new ClickhousePlugin());
        mapPlugin.put(JobTypeEnums.CONFIG.getCode(), new ConfigPlugin());
        mapPlugin.put(JobTypeEnums.FTP.getCode(), new FtpPlugin());
        mapPlugin.put(JobTypeEnums.FTP.getCode(), new SftpPlugin());
        mapPlugin.put(JobTypeEnums.GROOVY.getCode(), new GroovyPlugin());
        mapPlugin.put(JobTypeEnums.MYSQL.getCode(), new MySQLPlugin());
    }

    /**
     * 根据脚本插件名称获取相应的脚本执行器
     * @param pluginName 脚本插件名称
     * @return 对应的脚本执行器
     */
    public static AbstractExecutor getRunnerPlugin(String pluginName) {
        return mapPlugin.get(pluginName);
    }
}
