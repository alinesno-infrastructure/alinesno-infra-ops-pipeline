package com.alinesno.infra.ops.pipeline.command.utils;

/**
 * 配置
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class CommandUtil {

	public static class Unix {

		public final static String LINE_SEPARATOR = "\n";

		public final static String PATH_SEPARATOR = "/";

		public final static String CMD_EXECUTOR = "/bin/bash";
	}

	public static class Mac {

		public final static String LINE_SEPARATOR = "\n";

		public final static String PATH_SEPARATOR = "/";

		public final static String CMD_EXECUTOR = "/bin/bash";

	}

	public static class Windows {

		public final static String LINE_SEPARATOR = "\r\n";

		public final static String PATH_SEPARATOR = "\\";

		public final static String CMD_EXECUTOR = "C:\\cygwin\\bin\\bash.exe";
	}
}
