package com.alinesno.infra.ops.pipeline.dto;

import com.alinesno.infra.ops.pipeline.enums.JobTypeEnums;
import com.alinesno.infra.ops.pipeline.enums.StepTypeEnums;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class TaskInfoDtoTest {

    /**
     * 生成插件信息
     */
    @Test
    void testTaskInfoDto(){

        TaskInfoDto taskInfoDto = new TaskInfoDto() ;

        taskInfoDto.setName("脚本依赖信息");
        taskInfoDto.setDescribe("测试任务流程信息");
        taskInfoDto.setTaskType("workflow");

        // 配置 settings 文件
        List<ValueAttributeDto> settings = new ArrayList<>() ;
        settings.add(new ValueAttributeDto("cron" , "*/30 * * * * ?")) ;

        // 配置 env 文件
        List<ValueAttributeDto> env = new ArrayList<>() ;
        env.add(new ValueAttributeDto("JAVA_HOME" , "/ops/java")) ;
        env.add(new ValueAttributeDto("GIT_TOKEN" , "xxxx")) ;

        // 配置流程
        List<TaskStepInfo> workflow = new ArrayList<>() ;

        TaskStepInfo node_01 = new TaskStepInfo() ;
        node_01.setName("初始化环境配置");
        node_01.setId("node_01");
        node_01.setType(StepTypeEnums.START.getCode());

        PluginDto pluginDto = new PluginDto() ;
        pluginDto.setName(JobTypeEnums.SHELL.getCode()) ;

        String script = "#!/bin/bash\n" +
                "\n" +
                "for ((i=1; i<=1000; i++))\n" +
                "do\n" +
                "    echo \"循环次数: $i\"\n" +
                "    # 在这里添加你要执行的命令或操作\n" +
                "done\n" ;
        pluginDto.setContextScript(script);

        node_01.setPlugin(pluginDto);
        node_01.setParentStep("start");
        workflow.add(node_01) ;

        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        TaskStepInfo  node_clickhouse = new TaskStepInfo() ;
        node_clickhouse.setName("查询运行指标");
        node_clickhouse.setId("node_02");
        node_clickhouse.setType("node");

        PluginDto pluginCk = new PluginDto() ;
        pluginCk.setName(JobTypeEnums.CLICKHOUSE.getCode()) ;

        List<ValueAttributeDto> valueAttributeDtos = new ArrayList<>() ;
        valueAttributeDtos.add(new ValueAttributeDto("query_sql" , "select count(1) from kfinfo"))  ;
        valueAttributeDtos.add(new ValueAttributeDto("driver_class" , "ru.yandex.clickhouse.ClickHouseDriver"))  ;
        valueAttributeDtos.add(new ValueAttributeDto("url" , "jdbc:clickhouse://localhost:8123/default"))  ;
        valueAttributeDtos.add(new ValueAttributeDto("username" , "default"))  ;
        valueAttributeDtos.add(new ValueAttributeDto("password" , ""))  ;

        pluginCk.setPluginAttributes(valueAttributeDtos);

        node_clickhouse.setPlugin(pluginCk);
        node_clickhouse.setParentStep(node_01.getId());
        workflow.add(node_clickhouse) ;
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        TaskStepInfo  node_02 = new TaskStepInfo() ;
        node_02.setName("发送消息通知");
        node_02.setId("node_03");
        node_02.setType("node");

        PluginDto pluginAlert = new PluginDto() ;
        pluginAlert.setName(JobTypeEnums.SHELL.getCode()) ;
        pluginAlert.setContextScript("echo 'send alert'");

        node_02.setPlugin(pluginAlert);
        node_02.setParentStep(StepTypeEnums.END.getCode());
        node_02.setParentStep(node_clickhouse.getId());
        workflow.add(node_02) ;

        taskInfoDto.setSettings(settings);
        taskInfoDto.setEnv(env);
        taskInfoDto.setWorkflow(workflow);

        System.out.println(new Gson().toJson(taskInfoDto));

    }

}