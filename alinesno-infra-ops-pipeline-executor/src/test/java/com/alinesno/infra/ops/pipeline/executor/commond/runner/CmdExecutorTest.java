package com.alinesno.infra.ops.pipeline.executor.commond.runner;

import com.alinesno.infra.ops.pipeline.command.bean.ScriptDto;
import com.alinesno.infra.ops.pipeline.command.domain.CmdResult;
import com.alinesno.infra.ops.pipeline.command.runner.CmdExecutor;
import com.alinesno.infra.ops.pipeline.command.runner.Log;
import com.alinesno.infra.ops.pipeline.command.runner.LogListener;
import com.alinesno.infra.ops.pipeline.command.runner.ProcListener;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 运行cmd配置
 */
public class CmdExecutorTest {

    @Test
    void testRunCmd(){

        ScriptDto dto = new ScriptDto() ;

        String scriptStr = "#!/bin/bash\n" +
                "\n" +
                "for ((i=1; i<=1000; i++))\n" +
                "do\n" +
                "    echo \"循环次数: $i\"\n" +
                "    # 在这里添加你要执行的命令或操作\n" +
                "done\n" ;

        dto.setScriptContext(scriptStr);

        CmdExecutor executor = new CmdExecutor(new NullProcListener(dto),
                new AnsibelLogListener(dto),
                null,
                null,
                Lists.newArrayList("ANSIBLE_SHELL_RUNNER"),
                null,
                Lists.newArrayList(dto.getScriptContext()));
        CmdResult result = executor.run();

        System.out.println("result = " + result);
    }

    private static class NullProcListener implements ProcListener {

        private final Logger logProc = LoggerFactory.getLogger(NullProcListener.class);
        private ScriptDto dto ;

        public NullProcListener(ScriptDto dto) {
            this.dto = dto ;
        }

        @Override
        public void onStarted(CmdResult result) {
            System.out.println("---> onStarted ,  result = " + result.toJson());
            logProc.info("开始运行安装脚本:{}", result.toJson());
        }

        @Override
        public void onLogged(CmdResult result) {
            System.out.println("---> onLogged ,  result = " + result.toJson());
            logProc.info(result.toJson());
        }

        @Override
        public void onExecuted(CmdResult result) {
            System.out.println("---> onExecuted ,  result = " + result);
            logProc.info("运行脚本中:{}", result.toJson());
        }

        @Override
        public void onException(CmdResult result) {
            System.out.println("---> onException ,  result = " + result);
            logProc.error("运行脚本异常:{}", result);
        }
    }

    private static class AnsibelLogListener implements LogListener {

        private final Logger logProc = LoggerFactory.getLogger(AnsibelLogListener.class);
        private boolean isFinish = false;
        private String cmdLogContent = null;

        public AnsibelLogListener(ScriptDto dto) {
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
            System.out.println("读写完成.");
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

    };

}