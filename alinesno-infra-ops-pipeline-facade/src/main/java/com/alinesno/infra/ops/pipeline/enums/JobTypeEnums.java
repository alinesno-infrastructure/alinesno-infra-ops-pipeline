package com.alinesno.infra.ops.pipeline.enums;

/**
 * 任务类型枚举
 */
public enum JobTypeEnums {

    SHELL("shell", "Shell脚本任务", "执行Shell脚本"),
    REMOTE_SHELL("remote-shell", "Shell脚本远程执行", "执行Shell远程脚本"),
    ALERT("alert", "告警任务", "发送告警通知"),
    ANSIBLE("ansible", "Ansible任务", "执行Ansible脚本"),
    DOWNLOAD("download", "下载任务", "执行Download任务"),
    CLICKHOUSE("clickhouse", "Clickhouse任务", "执行Clickhouse操作"),
    CONFIG("config", "配置任务", "执行配置相关操作"),
    FTP("ftp", "FTP任务", "执行FTP操作"),
    SFTP("sftp", "SFTP任务", "执行SFTP操作"),
    GROOVY("groovy", "Groovy任务", "执行Groovy脚本"),
    MYSQL("mysql", "MySQL任务", "执行MySQL操作");

    private final String code;
    private final String label;
    private final String description;

    JobTypeEnums(String code, String label, String description) {
        this.code = code;
        this.label = label;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    public static JobTypeEnums getEnumByCode(String code) {
        for (JobTypeEnums enumValue : JobTypeEnums.values()) {
            if (enumValue.getCode().equals(code)) {
                return enumValue;
            }
        }
        return null; // 如果没有匹配的枚举常量，则返回null或抛出异常，根据实际情况决定
    }
}
