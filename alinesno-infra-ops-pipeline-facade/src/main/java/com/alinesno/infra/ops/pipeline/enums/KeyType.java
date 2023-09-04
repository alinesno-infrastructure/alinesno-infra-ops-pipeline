package com.alinesno.infra.ops.pipeline.enums;

/**
 * 密钥类型枚举类，包含不同类型的密钥代码和标签。
 */
public enum KeyType {

    DATABASE("database", "数据库"),    // 数据库密钥类型
    SERVER("server", "服务器"),        // 服务器密钥类型
    WEBHOOK("webhook", "Web钩子"),     // Web钩子密钥类型
    GIT("git", "Git"),                  // Git密钥类型
    NORMAL("normal", "普通");           // 普通密钥类型

    private final String code;   // 密钥类型代码
    private final String label;  // 密钥类型标签

    /**
     * 枚举类构造方法
     * @param code 密钥类型代码
     * @param label 密钥类型标签
     */
    KeyType(String code, String label) {
        this.code = code;
        this.label = label;
    }

    /**
     * 获取密钥类型代码
     * @return 密钥类型代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 获取密钥类型标签
     * @return 密钥类型标签
     */
    public String getLabel() {
        return label;
    }
}
