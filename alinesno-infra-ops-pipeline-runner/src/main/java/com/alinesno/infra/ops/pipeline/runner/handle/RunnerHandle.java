package com.alinesno.infra.ops.pipeline.runner.handle;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteResultHandler;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RunnerHandle {

    public static void main(String[] args) {
        // 从命令行参数获取要执行的任务命令
        if (args.length == 0) {
            System.out.println("Please provide a task command.");
            return;
        }
        String taskCommand = args[0];

        // 创建命令行对象
        CommandLine commandLine = CommandLine.parse(taskCommand);

        // 创建执行器对象
        Executor executor = new DefaultExecutor();

        // 设置超时时间为5分钟
        ExecuteWatchdog watchdog = new ExecuteWatchdog(TimeUnit.MINUTES.toMillis(5));
        executor.setWatchdog(watchdog);

        // 设置输出流处理器，用于捕获任务输出
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
        executor.setStreamHandler(streamHandler);

        try {
            // 异步执行任务命令
            executor.execute(commandLine, new ExecuteResultHandler() {
                @Override
                public void onProcessComplete(int exitValue) {
                    System.out.println("Task completed with exit code: " + exitValue);
                    String output = outputStream.toString();
                    System.out.println("Task output:\n" + output);
                }

                @Override
                public void onProcessFailed(ExecuteException e) {
                    System.out.println("Task execution failed: " + e.getMessage());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
