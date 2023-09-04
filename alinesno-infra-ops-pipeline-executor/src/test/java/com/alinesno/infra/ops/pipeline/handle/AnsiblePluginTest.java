package com.alinesno.infra.ops.pipeline.handle;

import com.alinesno.infra.ops.pipeline.command.bean.AnsibleScriptDto;
import com.alinesno.infra.ops.pipeline.command.domain.CmdResult;
import com.alinesno.infra.ops.pipeline.command.runner.CmdExecutor;
import com.alinesno.infra.ops.pipeline.command.runner.Log;
import com.alinesno.infra.ops.pipeline.command.runner.LogListener;
import com.alinesno.infra.ops.pipeline.command.runner.ProcListener;
import com.alinesno.infra.ops.pipeline.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.pipeline.dto.ValueAttributeDto;
import com.alinesno.infra.ops.pipeline.exception.ExecutorServiceRuntimeException;
import com.alinesno.infra.ops.pipeline.utils.AttributeUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * 单元测试类：AnsiblePlugin
 */
@DisplayName("AnsiblePlugin 单元测试")
class AnsiblePluginTest {

    @Test
    @DisplayName("成功执行")
    void run_SuccessfulExecution() throws IOException {
        // 创建 AnsiblePlugin 实例
        AnsiblePlugin ansiblePlugin = new AnsiblePlugin();

        // 创建 ExecutorScriptDto 对象，并设置必要的属性
        ExecutorScriptDto executorScriptDto = new ExecutorScriptDto();
        executorScriptDto.setScriptContent("playbook.yml"); // 设置剧本内容
        executorScriptDto.getAttributes().add(new ValueAttributeDto("inventory", "inventory.ini")); // 设置清单文件路径

        // 创建上下文映射
        Map<String, Object> contextMap = new HashMap<>();

        // 创建 AnsibleScriptDto 对象，并设置必要的属性
        AnsibleScriptDto ansibleScriptDto = new AnsibleScriptDto();
        ansibleScriptDto.setJobWorkspace("/path/to/workspace");
        ansibleScriptDto.setPlaybook(executorScriptDto.getScriptContent());
        // ansibleScriptDto.setInventory((String) executorScriptDto.getAttributes().get("inventory"));
        ansibleScriptDto.setInstallation("/path/to/ansible");
        ansibleScriptDto.setExtras("");

        // 创建 Mock 对象并设置预期行为
        CmdExecutor cmdExecutorMock = mock(CmdExecutor.class);
        CmdResult cmdResultMock = new CmdResult();
        cmdResultMock.setExitValue(0);
        when(cmdExecutorMock.run()).thenReturn(cmdResultMock);

        // 使用 Mockito 的 ArgumentCaptor 捕获传递给 CmdExecutor 构造函数的参数
        ArgumentCaptor<ProcListener> procListenerCaptor = ArgumentCaptor.forClass(ProcListener.class);
        ArgumentCaptor<LogListener> logListenerCaptor = ArgumentCaptor.forClass(LogListener.class);
        ArgumentCaptor<String> scriptArgumentCaptor = ArgumentCaptor.forClass(String.class);

        // 将 Mock 对象注入到 AnsiblePlugin 中
        ansiblePlugin.setCmdExecutor(cmdExecutorMock);

        // 执行 Ansible 相关任务
        ansiblePlugin.run(executorScriptDto, contextMap);

        // 验证预期行为
        verify(cmdExecutorMock).run();
        verify(cmdExecutorMock).setProcListener(procListenerCaptor.capture());
        verify(cmdExecutorMock).setLogListener(logListenerCaptor.capture());
        verify(cmdExecutorMock).setCommandArguments(Collections.singletonList(scriptArgumentCaptor.capture()));

        // 验证传递给 CmdExecutor 的参数是否符合预期
        ProcListener procListener = procListenerCaptor.getValue();
        assertNotNull(procListener);
        procListener.onStarted(cmdResultMock);
        procListener.onLogged(cmdResultMock);
        procListener.onExecuted(cmdResultMock);

        LogListener logListener = logListenerCaptor.getValue();
        assertNotNull(logListener);
        logListener.onLog(new Log("Log message"));
        logListener.onFinish();

        String scriptArgument = scriptArgumentCaptor.getValue();
        assertNotNull(scriptArgument);
        assertTrue(scriptArgument.contains(ansibleScriptDto.getInstallation()));
        assertTrue(scriptArgument.contains("-i " + ansibleScriptDto.getInventory()));
        assertTrue(scriptArgument.contains(ansibleScriptDto.getPlaybook()));

        // 验证结果是否符合预期
        // ...
    }

    @Test
    @DisplayName("执行失败")
    void run_FailedExecution() throws IOException {
        // 创建 AnsiblePlugin 实例
        AnsiblePlugin ansiblePlugin = new AnsiblePlugin();

        // 创建 ExecutorScriptDto 对象，并设置必要的属性
        ExecutorScriptDto executorScriptDto = new ExecutorScriptDto();
        executorScriptDto.setScriptContent("playbook.yml"); // 设置剧本内容
//        executorScriptDto.getAttributes().put("inventory", "inventory.ini"); // 设置清单文件路径

        // 创建上下文映射
        Map<String, Object> contextMap = new HashMap<>();

        // 创建 AnsibleScriptDto 对象，并设置必要的属性
        AnsibleScriptDto ansibleScriptDto = new AnsibleScriptDto();
        ansibleScriptDto.setJobWorkspace("/path/to/workspace");
        ansibleScriptDto.setPlaybook(executorScriptDto.getScriptContent());
        ansibleScriptDto.setInventory((String) AttributeUtils.convertAttributesToMap(executorScriptDto.getAttributes()).get("inventory"));
        ansibleScriptDto.setInstallation("/path/to/ansible");
        ansibleScriptDto.setExtras("");

        // 创建 Mock 对象并设置预期行为
        CmdExecutor cmdExecutorMock = mock(CmdExecutor.class);
        CmdResult cmdResultMock = new CmdResult();
        cmdResultMock.setExitValue(1);
        when(cmdExecutorMock.run()).thenReturn(cmdResultMock);

        ProcListener procListenerCaptor = (ProcListener) ArgumentCaptor.forClass(ProcListener.class);
        ArgumentCaptor<LogListener> logListenerCaptor = ArgumentCaptor.forClass(LogListener.class);
        ArgumentCaptor<String> scriptArgumentCaptor = ArgumentCaptor.forClass(String.class);

        // 将 Mock 对象注入到 AnsiblePlugin 中
        ansiblePlugin.setCmdExecutor(cmdExecutorMock);

        // 执行 Ansible 相关任务
        assertThrows(ExecutorServiceRuntimeException.class, () -> {
            ansiblePlugin.run(executorScriptDto, contextMap);
        });

        // 验证预期行为
        verify(cmdExecutorMock).run();
        verify(cmdExecutorMock).setLogListener(logListenerCaptor.capture());
        verify(cmdExecutorMock).setCommandArguments(Collections.singletonList(scriptArgumentCaptor.capture()));

        LogListener logListener = logListenerCaptor.getValue();
        assertNotNull(logListener);
        logListener.onLog(new Log("Log message"));
        logListener.onFinish();

        String scriptArgument = scriptArgumentCaptor.getValue();
        assertNotNull(scriptArgument);
        assertTrue(scriptArgument.contains(ansibleScriptDto.getInstallation()));
        assertTrue(scriptArgument.contains("-i " + ansibleScriptDto.getInventory()));
        assertTrue(scriptArgument.contains(ansibleScriptDto.getPlaybook()));

        // 验证结果是否符合预期
        // ...
    }

    @Test
    @DisplayName("IOException 异常")
    void run_IOException() throws IOException {
        // 创建 AnsiblePlugin 实例
        AnsiblePlugin ansiblePlugin = new AnsiblePlugin();

        // 创建 ExecutorScriptDto 对象，并设置必要的属性
        ExecutorScriptDto executorScriptDto = new ExecutorScriptDto();
        executorScriptDto.setScriptContent("playbook.yml"); // 设置剧本内容
        executorScriptDto.getAttributes().add(new ValueAttributeDto("inventory", "inventory.ini")); // 设置清单文件路径

        // 创建上下文映射
        Map<String, Object> contextMap = new HashMap<>();

        // 创建 AnsibleScriptDto 对象，并设置必要的属性
        AnsibleScriptDto ansibleScriptDto = new AnsibleScriptDto();
        ansibleScriptDto.setJobWorkspace("/path/to/workspace");
        ansibleScriptDto.setPlaybook(executorScriptDto.getScriptContent());
        ansibleScriptDto.setInventory((String) AttributeUtils.convertAttributesToMap(executorScriptDto.getAttributes()).get("inventory"));
        ansibleScriptDto.setInstallation("/path/to/ansible");
        ansibleScriptDto.setExtras("");

        // 创建 Mock 对象并设置预期行为
        CmdExecutor cmdExecutorMock = mock(CmdExecutor.class);
        when(cmdExecutorMock.run()).thenThrow(IOException.class);

        // 将 Mock 对象注入到 AnsiblePlugin 中
        ansiblePlugin.setCmdExecutor(cmdExecutorMock);

        // 执行 Ansible 相关任务
        assertThrows(ExecutorServiceRuntimeException.class, () -> {
            ansiblePlugin.run(executorScriptDto, contextMap);
        });

        // 验证预期行为
        verify(cmdExecutorMock).run();

        // 验证结果是否符合预期
        // ...
    }
}
