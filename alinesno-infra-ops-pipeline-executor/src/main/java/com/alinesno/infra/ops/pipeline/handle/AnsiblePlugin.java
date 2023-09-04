package com.alinesno.infra.ops.pipeline.handle;

import com.alinesno.infra.ops.pipeline.AbstractExecutor;
import com.alinesno.infra.ops.pipeline.command.bean.AnsibleScriptDto;
import com.alinesno.infra.ops.pipeline.command.constants.ExecuteConst;
import com.alinesno.infra.ops.pipeline.command.domain.CmdResult;
import com.alinesno.infra.ops.pipeline.command.runner.CmdExecutor;
import com.alinesno.infra.ops.pipeline.command.runner.Log;
import com.alinesno.infra.ops.pipeline.command.runner.LogListener;
import com.alinesno.infra.ops.pipeline.command.runner.ProcListener;
import com.alinesno.infra.ops.pipeline.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.pipeline.exception.ExecutorServiceRuntimeException;
import com.alinesno.infra.ops.pipeline.utils.AttributeUtils;
import com.google.common.collect.Lists;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * AnsiblePlugin类是一个Ansible插件执行器，继承自AbstractExecutor抽象类。
 * 它用于执行Ansible相关任务。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class AnsiblePlugin extends AbstractExecutor {

    private static final Logger log = LoggerFactory.getLogger(AnsiblePlugin.class) ;
    private static final String WHITE_TEXT = " " ;

    private static final String INVENTORY_PATH = "inventory" ;

    @Override
    public void run(@NotNull ExecutorScriptDto executorScriptDto, Map<String, Object> contextMap) {
        // 获取配置属性
        Map<String , Object> attrs = AttributeUtils.convertAttributesToMap(executorScriptDto.getAttributes()) ;
        String inventory = (String) attrs.get(INVENTORY_PATH);

        // 在此方法中实现Ansible相关任务的具体逻辑

        AnsibleScriptDto dto = new AnsibleScriptDto() ;
        dto.setPlaybook(executorScriptDto.getScriptContent());
        dto.setInventory(inventory);

        log.debug("AnsibleScriptDto = {}" , dto);

        Assert.hasLength(dto.getJobWorkspace() , "空间路径不能为空");
        Assert.hasLength(dto.getPlaybook() , "运行剧本为空");
        Assert.hasLength(dto.getInventory() , "运行主机清单为空");
        Assert.hasLength(dto.getInstallation() , "运行路径不能为空");

        StringBuilder script = new StringBuilder() ;

        script.append(dto.getInstallation()) ;
        script.append(WHITE_TEXT) ;
        script.append("-i ").append(dto.getInventory());
        script.append(WHITE_TEXT) ;
        script.append(dto.getPlaybook()) ;

        if(StringUtils.isNotBlank(dto.getExtras())) {
            script.append(WHITE_TEXT) ;
            script.append(dto.getExtras()) ;
        }

        String scriptStr = script.toString() ;

        log.debug("运行ansible脚本:{}", scriptStr);

        CmdExecutor executor1 = new CmdExecutor(new NullProcListener(dto), new AnsibelLogListener(dto), null, null, Lists.newArrayList("ANSIBLE_SHELL_RUNNER"), null, Lists.newArrayList(scriptStr));
        CmdResult result1 = executor1.run();

        if(result1.getExitValue() != 0) {
            String logPath = dto.getJobWorkspace() + File.separator + ExecuteConst.running_logger_fileName ;
            String logStr = "" ;
            try {
                logStr = FileUtils.readFileToString(new File(logPath), Charset.defaultCharset());
            } catch (IOException e) {
                log.error("找不到文件:{}" , logPath);
            }
            throw new ExecutorServiceRuntimeException("脚本【" + dto.getPlaybook() + "】运行异常，无法正常运作.\r\n" + logStr ) ;
        }

        log.debug("result = {}", result1);

    }

    private static class NullProcListener implements ProcListener {

        private final Logger logPro = LoggerFactory.getLogger(NullProcListener.class);
        private final AnsibleScriptDto AnsibleScriptDto ;

        public NullProcListener(AnsibleScriptDto dto) {

            this.AnsibleScriptDto = dto ;

            logPro.debug("dto = {}" , this.AnsibleScriptDto);

        }

        @Override
        public void onStarted(CmdResult result) {
            log.debug("---> onStarted ,  result = " + result.toJson());
            logPro.info("开始运行安装脚本:{}", result.toJson());
        }

        @Override
        public void onLogged(CmdResult result) {
            log.debug("---> onLogged ,  result = " + result.toJson());
            logPro.info(result.toJson());

            AnsibleScriptDto.setRunLogger(result.toJson());
        }

        @Override
        public void onExecuted(CmdResult result) {
            log.debug("---> onExecuted ,  result = " + result.toJson());
            logPro.info("运行脚本中:{}", result.toJson());
        }

        @Override
        public void onException(CmdResult result) {
            log.debug("---> onException ,  result = " + result.toJson());
            logPro.error("运行脚本异常:{}", result.toJson());
        }
    }

    private static class AnsibelLogListener implements LogListener {

        private final Logger logPro = LoggerFactory.getLogger(AnsibelLogListener.class);
        private boolean isFinish = false;
        private String cmdLogContent = null;

        public AnsibelLogListener(AnsibleScriptDto dto) {

            String logPath = dto.getJobWorkspace() + File.separator + ExecuteConst.running_logger_fileName;

            logPro.debug("dto = {}" , dto);
        }

        @Override
        public void onLog(Log loger) {
            cmdLogContent = loger.getContent();

            if (cmdLogContent.contains("command not found")) {
                isFinish = false;
                throw new ExecutorServiceRuntimeException("命令行未找到:" + cmdLogContent);
            }

            logPro.info(cmdLogContent);
        }

        @Override
        public void onFinish() {
            log.debug("读写完成.");
            logPro.info("脚本运行结束.");
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
