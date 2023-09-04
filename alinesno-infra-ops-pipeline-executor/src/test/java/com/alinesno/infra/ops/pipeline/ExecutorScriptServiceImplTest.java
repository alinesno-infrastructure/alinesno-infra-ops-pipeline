package com.alinesno.infra.ops.pipeline;

import com.alinesno.infra.ops.pipeline.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.pipeline.dto.TaskInfoDto;
import com.alinesno.infra.ops.pipeline.dto.TaskStepInfo;
import com.alinesno.infra.ops.pipeline.entity.JobEntity;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExecutorScriptServiceImplTest {

    private ExecutorScriptServiceImpl executorScriptService;

    private static final Gson gson = new Gson();

    public ExecutorScriptServiceImplTest() {
        MockitoAnnotations.openMocks(this);
        executorScriptService = new ExecutorScriptServiceImpl();
    }

    @Test
    @DisplayName("测试 run 方法")
    public void testRun() {
        // 准备测试数据
        ExecutorScriptDto executorScriptDto = new ExecutorScriptDto();
        executorScriptDto.setType("scriptType");
        Map<String, Object> contextMap = new HashMap<>();

        // 调用被测试方法
        executorScriptService.run(executorScriptDto, contextMap);
    }

    @Test
    @DisplayName("测试 analyzeTaskInfo 方法")
    public void testAnalyzeTaskInfo() {
        // 准备测试数据
        TaskInfoDto taskInfoDto = new TaskInfoDto();
        taskInfoDto.setName("Test Task");
        taskInfoDto.setDescribe("This is a test task");
        taskInfoDto.setWorkflow(createTestWorkflow());

        // 调用被测试方法
        JobEntity result = executorScriptService.analyzeTaskInfo(taskInfoDto);

        // 验证方法的行为
        ArgumentCaptor<JobEntity> jobEntityCaptor = ArgumentCaptor.forClass(JobEntity.class);

        // 验证保存的 JobEntity 对象是否符合预期
        JobEntity savedJobEntity = jobEntityCaptor.getValue();
        assertEquals(taskInfoDto.getName(), savedJobEntity.getName());
        assertEquals(taskInfoDto.getDescribe(), savedJobEntity.getDescription());
        assertEquals(gson.toJson(taskInfoDto), savedJobEntity.getJobContext());
        assertEquals(result, savedJobEntity);
    }

    // 创建测试用的 Workflow 数据
    private List<TaskStepInfo> createTestWorkflow() {
        List<TaskStepInfo> workflow = new ArrayList<>();

        TaskStepInfo step1 = new TaskStepInfo();
        step1.setType("step1");
        step1.setName("Step 1");
        workflow.add(step1);

        TaskStepInfo step2 = new TaskStepInfo();
        step2.setType("step2");
        step2.setName("Step 2");
        workflow.add(step2);

        return workflow;
    }
}
