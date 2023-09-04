package com.alinesno.infra.ops.pipeline.api.provider;

import com.alinesno.infra.common.core.context.SpringContext;
import com.alinesno.infra.common.facade.response.AjaxResult;
import com.alinesno.infra.ops.pipeline.dto.TaskInfoDto;
import com.alinesno.infra.ops.pipeline.entity.JobEntity;
import com.alinesno.infra.ops.pipeline.service.IDistSchedulerService;
import com.alinesno.infra.ops.pipeline.service.IExecutorScriptService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 构建任务对外服务
 */
@Scope
@RestController
@RequestMapping("/v1/api/ops/scheduler/")
public class OpsScriptController {

    private static final Logger log = LoggerFactory.getLogger(OpsScriptController.class);

    @Autowired
    private IExecutorScriptService executorScriptService ;

    /**
     * 运行数据抽取服务
     *
     * @param dto 执行器脚本的DTO对象，包含执行器脚本的相关信息
     * @return 返回执行结果的AjaxResult对象
     * @throws SQLException 如果执行过程中发生SQL异常
     * @throws IOException  如果执行过程中发生IO异常
     */
    @PostMapping("defineTask")
    public AjaxResult runPipeline(@RequestBody TaskInfoDto dto) throws SQLException, IOException {

        log.debug("script dto = {}", new Gson().toJson(dto));

        JobEntity job =  executorScriptService.analyzeTaskInfo(dto) ;

        return AjaxResult.success("任务保存成功." , job.getId());
    }


    /**
     * 运行数据抽取服务
     *
     * @param jobId 执行器脚本的DTO对象，包含执行器脚本的相关信息
     * @return 返回执行结果的AjaxResult对象
     * @throws SQLException 如果执行过程中发生SQL异常
     * @throws IOException  如果执行过程中发生IO异常
     */
    @GetMapping("runTask")
    public AjaxResult runTask(String jobId) throws SQLException, IOException {

        // 使用Spring上下文获取IDistSchedulerService实例
        IDistSchedulerService distSchedulerService = SpringContext.getBean(IDistSchedulerService.class);
        distSchedulerService.createCronJob(Long.parseLong(jobId));

        return AjaxResult.success("任务执行成功.") ;
    }
}
