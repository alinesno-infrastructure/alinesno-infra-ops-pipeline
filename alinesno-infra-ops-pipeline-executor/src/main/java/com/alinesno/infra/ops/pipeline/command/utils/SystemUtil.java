package com.alinesno.infra.ops.pipeline.command.utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.regex.Pattern;

import com.google.common.base.Strings;

/**
 * @author yang
 * @author luoxiaodong
 * @version 1.0.0
 */
public class SystemUtil {

    private final static char ENV_VAR_START_CHAR = '$';
    private final static char ENV_VAR_LEFT_BRACKET = '{';
    @SuppressWarnings("unused")
	private final static char ENV_VAR_RIGHT_BRACKET = '}';

    public static boolean isWindows() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.startsWith("win");
    }
   
    /**
     * 判断是否为mac系统
     * @return
     */
    public static boolean isMac() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.startsWith("mac");
    }

    /**
     * Parse path with ${xxx} variable to absolute path
     *
     * @param pathWithEnv path with folder/${xxx}/folder
     * @return absolute path
     */
    public static Path replacePathWithEnv(String pathWithEnv) {
        String[] paths = pathWithEnv.split(Pattern.quote(File.separator));

        StringBuilder builder = new StringBuilder();

        for (String pathItem : paths) {
            int index = pathItem.indexOf("$", 0);

            if (index < 0) {
                builder.append(pathItem).append(File.separator);
                continue;
            }

            builder.append(parseEnv(pathItem)).append(File.separator);
        }

        return Paths.get(builder.toString());
    }

    /**
     * Parse ${xx} variable to exact value
     */
    public static String parseEnv(String env) {
        if (Objects.isNull(env)) {
            throw new IllegalArgumentException();
        }

        if (env.charAt(0) != ENV_VAR_START_CHAR) {
            throw new IllegalArgumentException();
        }

        boolean hasBracket = env.charAt(1) == ENV_VAR_LEFT_BRACKET;
        env = env.substring(1);

        if (!hasBracket) {
            return getEnvOrProperty(env);
        }

        env = env.substring(1, env.length() - 1);
        return getEnvOrProperty(env);
    }

    private static String getEnvOrProperty(String name) {
        String value = System.getenv(name);

        if (Strings.isNullOrEmpty(value)) {
            return System.getProperty(name);
        }

        return value;
    }
}
