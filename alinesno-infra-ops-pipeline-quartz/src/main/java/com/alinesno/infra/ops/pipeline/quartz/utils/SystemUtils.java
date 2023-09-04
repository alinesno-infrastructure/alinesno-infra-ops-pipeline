package com.alinesno.infra.ops.pipeline.quartz.utils;

/**
 * SystemUtils类提供了与系统相关的实用方法。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class SystemUtils {

    /**
     * 格式化给定的毫秒数为更可读的时间格式。
     *
     * @param milliseconds 要格式化的毫秒数
     * @return 格式化后的时间字符串，如"2分钟30秒500毫秒"
     */
    public static String formatMilliseconds(long milliseconds) {
        long minutes = milliseconds / (1000 * 60);
        long seconds = (milliseconds / 1000) % 60;
        long remainingMilliseconds = milliseconds % 1000;

        StringBuilder formattedTime = new StringBuilder();

        if (minutes > 0) {
            formattedTime.append(minutes).append("分钟");
        }

        if (seconds > 0 || remainingMilliseconds > 0) {
            formattedTime.append(seconds).append("秒");
        }

        if (remainingMilliseconds > 0) {
            formattedTime.append(remainingMilliseconds).append("毫秒");
        }

        return formattedTime.toString();
    }
}
