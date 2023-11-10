package com.alinesno.infra.ops.pipeline;

import com.alinesno.infra.common.core.context.SpringContext;
import com.alinesno.infra.common.facade.enable.EnableActable;
import com.alinesno.infra.common.web.adapter.sso.enable.EnableInfraSsoApi;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 集成一个Java开发示例工具
 * @author luoxiaodong
 * @version 1.0.0
 */
@EnableActable
@EnableInfraSsoApi
@MapperScan("com.alinesno.infra.ops.pipeline.mapper")
@SpringBootApplication
public class OpsPipelineApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpsPipelineApplication.class, args);
	}


	@Bean
	public SpringContext getSpringContext(){
		return new SpringContext() ;
	}

}