package com.alinesno.infra.ops.pipeline.handle;

import com.alinesno.infra.ops.pipeline.command.domain.CmdResult;
import com.alinesno.infra.ops.pipeline.command.runner.CmdExecutor;
import com.alinesno.infra.ops.pipeline.dto.ExecutorScriptDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

class ShellPluginTest {

    @Mock
    private ExecutorScriptDto executorScriptDto;

    @BeforeEach
    void setUp() {
        // 初始化 Mockito 注解
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void run_ShouldExecuteShellScriptAndPrintResult_WhenDtoIsValid() {

        // 运行命令
        String command = """
                cd /Users/luodong/Desktop
                export PROJECT=dubbo
                rm -rf dubbo \s
                echo 'PROJECT = ${ PROJECT }'
                git clone https://github.com/apache/dubbo.git \s
                cd dubbo \s
                mvn clean package\s
                """;

        executorScriptDto.setScriptContent(command);

        // 准备
        ShellPlugin shellPlugin = new ShellPlugin();
        Map<String, Object> contextMap = new HashMap<>();

        // 设置模拟的CmdResult
        CmdResult cmdResult = createMockCmdResult();
        CmdExecutor cmdExecutor = mock(CmdExecutor.class);
        when(cmdExecutor.run()).thenReturn(cmdResult);

        // 执行
        shellPlugin.run(executorScriptDto, contextMap);
    }

    private CmdResult createMockCmdResult() {
        // 创建模拟的 CmdResult
        CmdResult cmdResult = new CmdResult();
//        cmdResult.setOutput("Hello, World!");
        return cmdResult;
    }
}
