package com.alinesno.infra.ops.pipeline.api.provider;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * DeploymentController 是一个 RESTful API 控制器，用于处理部署请求。
 */
@RestController
public class DeploymentController {

    /**
     * 处理部署请求。
     *
     * @param commitId 提交ID
     * @param branch   分支名称
     * @return 响应实体
     */
    @GetMapping("/deploy")
    public ResponseEntity<String> handleDeploymentRequest(
            @RequestParam("commitId") String commitId,
            @RequestParam("branch") String branch
    ) {
        // 执行发布逻辑，例如构建、部署等
        // ...

        String responseMessage = "Deployment triggered for commit " + commitId + " on branch " + branch;
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }
}
