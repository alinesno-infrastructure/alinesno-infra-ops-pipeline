package com.alinesno.infra.ops.pipeline.api.provider;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * 单元测试类：QuartzJobsVO
 */
@DisplayName("QuartzJobsVO 单元测试")
class QuartzJobsVOTest {

    @Test
    @DisplayName("测试获取和设置 JobDetail 的名称")
    void testGetAndSetJobDetailName() {
        // 创建 QuartzJobsVO 实例
        QuartzJobsVO quartzJobsVO = new QuartzJobsVO();

        // 设置 JobDetail 的名称
        String jobDetailName = "job1";
        quartzJobsVO.setJobDetailName(jobDetailName);

        // 获取 JobDetail 的名称，并验证是否与预期相符
        String retrievedJobDetailName = quartzJobsVO.getJobDetailName();
        assertNotNull(retrievedJobDetailName);
        assertEquals(jobDetailName, retrievedJobDetailName);
    }

    @Test
    @DisplayName("测试获取和设置 JobDetail 的组名")
    void testGetAndSetGroupName() {
        // 创建 QuartzJobsVO 实例
        QuartzJobsVO quartzJobsVO = new QuartzJobsVO();

        // 设置 JobDetail 的组名
        String groupName = "group1";
        quartzJobsVO.setGroupName(groupName);

        // 获取 JobDetail 的组名，并验证是否与预期相符
        String retrievedGroupName = quartzJobsVO.getGroupName();
        assertNotNull(retrievedGroupName);
        assertEquals(groupName, retrievedGroupName);
    }

    @Test
    @DisplayName("测试获取和设置 Job 的 Cron 表达式或触发器的键")
    void testGetAndSetJobCronExpression() {
        // 创建 QuartzJobsVO 实例
        QuartzJobsVO quartzJobsVO = new QuartzJobsVO();

        // 设置 Job 的 Cron 表达式或触发器的键
        String jobCronExpression = "0 0/5 * * * ?";
        quartzJobsVO.setJobCronExpression(jobCronExpression);

        // 获取 Job 的 Cron 表达式或触发器的键，并验证是否与预期相符
        String retrievedJobCronExpression = quartzJobsVO.getJobCronExpression();
        assertNotNull(retrievedJobCronExpression);
        assertEquals(jobCronExpression, retrievedJobCronExpression);
    }

    @Test
    @DisplayName("测试获取和设置 Job 或 Trigger 的状态")
    void testGetAndSetStatus() {
        // 创建 QuartzJobsVO 实例
        QuartzJobsVO quartzJobsVO = new QuartzJobsVO();

        // 设置 Job 或 Trigger 的状态
        String status = "ACTIVE";
        quartzJobsVO.setStatus(status);

        // 获取 Job 或 Trigger 的状态，并验证是否与预期相符
        String retrievedStatus = quartzJobsVO.getStatus();
        assertNotNull(retrievedStatus);
        assertEquals(status, retrievedStatus);
    }
}
