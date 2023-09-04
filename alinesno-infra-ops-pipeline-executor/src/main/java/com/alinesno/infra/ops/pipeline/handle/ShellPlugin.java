package com.alinesno.infra.ops.pipeline.handle;

import com.alinesno.infra.ops.pipeline.AbstractExecutor;
import com.alinesno.infra.ops.pipeline.command.domain.CmdResult;
import com.alinesno.infra.ops.pipeline.command.runner.CmdExecutor;
import com.alinesno.infra.ops.pipeline.command.runner.Log;
import com.alinesno.infra.ops.pipeline.command.runner.LogListener;
import com.alinesno.infra.ops.pipeline.command.runner.ProcListener;
import com.alinesno.infra.ops.pipeline.dto.ExecutorScriptDto;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * ShellPlugin类是一个Shell插件执行器，继承自AbstractExecutor抽象类。
 * 它用于执行Shell脚本任务。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class ShellPlugin extends AbstractExecutor {

    private static final Logger log = LoggerFactory.getLogger(ShellPlugin.class) ;

    @Override
    public void run(ExecutorScriptDto dto, Map<String, Object> contextMap) {

        // 创建CmdExecutor对象，并设置ProcListener和LogListener
        CmdExecutor executor = new CmdExecutor(new NullProcListener(dto),
                new AnsibelLogListener(dto),
                null,
                null,
                Lists.newArrayList("SHELL_RUNNER"),
                null,
                Lists.newArrayList(dto.getScriptContent()));

        // 运行脚本任务并获取执行结果
        CmdResult result = executor.run();

        log.debug("result = " + result);
    }

    /**
     * NullProcListener类是一个空的ProcListener实现类。
     * 它用于处理CmdExecutor执行过程中的回调事件。
     */
    private static class NullProcListener implements ProcListener {

        private final Logger logProc = LoggerFactory.getLogger(NullProcListener.class);
        private ExecutorScriptDto dto ;

        public NullProcListener(ExecutorScriptDto dto) {
            this.dto = dto ;
        }

        @Override
        public void onStarted(CmdResult result) {
            log.debug("---> onStarted ,  result = " + result.toJson());
            logProc.info("开始运行安装脚本:{}", result.toJson());
        }

        @Override
        public void onLogged(CmdResult result) {
            log.debug("---> onLogged ,  result = " + result.toJson());
            logProc.info(result.toJson());
        }

        @Override
        public void onExecuted(CmdResult result) {
            log.debug("---> onExecuted ,  result = " + result);
            logProc.info("运行脚本中:{}", result.toJson());
        }

        @Override
        public void onException(CmdResult result) {
            log.debug("---> onException ,  result = " + result);
            logProc.error("运行脚本异常:{}", result);
        }
    }

    /**
     * AnsibelLogListener类是一个LogListener实现类。
     * 它用于处理CmdExecutor执行过程中的日志输出事件。
     */
    private static class AnsibelLogListener implements LogListener {

        private final Logger logProc = LoggerFactory.getLogger(AnsibelLogListener.class);
        private boolean isFinish = false;
        private String cmdLogContent = null;

        public AnsibelLogListener(ExecutorScriptDto dto) {
            logProc.debug("dto = {}" , dto);
        }

        @Override
        public void onLog(Log log) {
            cmdLogContent = log.getContent();

            if (cmdLogContent.contains("command not found")) {
                isFinish = false;
                logProc.info("命令行未找到:" + cmdLogContent);
            }

            logProc.info(cmdLogContent);
        }

        @Override
        public void onFinish() {
            log.debug("读写完成.");
            logProc.info("脚本运行结束.");
            isFinish = true;
        }

        public boolean isFinish() {
            return isFinish;
        }

        @Override
        public String cmdLogContent() {
            return cmdLogContent;
        }
    }
}