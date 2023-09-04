package com.alinesno.infra.ops.pipeline.exception;

/**
 * ExecutorServiceRuntimeException 类是 RuntimeException 的子类，用于表示执行器服务运行时异常。
 * 可选地添加了异常错误码字段。
 *
 * @author luoxiaodong
 * @version  1.0.0
 */
public class ExecutorServiceRuntimeException extends RuntimeException {
    private String errorCode; // 添加异常错误码字段（可选）

    /**
     * 构造方法，创建一个 ExecutorServiceRuntimeException 实例。
     *
     * @param message 异常消息
     */
    public ExecutorServiceRuntimeException(String message) {
        super(message);
    }

    /**
     * 构造方法，创建一个 ExecutorServiceRuntimeException 实例。
     *
     * @param message 异常消息
     * @param cause 异常原因
     */
    public ExecutorServiceRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 构造方法，创建一个 ExecutorServiceRuntimeException 实例。
     *
     * @param message 异常消息
     * @param errorCode 异常错误码
     */
    public ExecutorServiceRuntimeException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * 构造方法，创建一个 ExecutorServiceRuntimeException 实例。
     *
     * @param message 异常消息
     * @param errorCode 异常错误码
     * @param cause 异常原因
     */
    public ExecutorServiceRuntimeException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    /**
     * 获取异常错误码。
     *
     * @return 异常错误码
     */
    public String getErrorCode() {
        return errorCode;
    }
}
