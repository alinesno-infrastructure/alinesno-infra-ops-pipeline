package com.alinesno.infra.ops.pipeline.runner.rest;


import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * GPT响应配置
 * @author luoxiaodong
 * @version 1.0.0
 */
@WebServlet(urlPatterns = "/api/process", asyncSupported = true )
public class ReactiveServlet extends HttpServlet {
	
	private static final Logger log = LoggerFactory.getLogger(ReactiveServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws IOException {
		
		resp.setContentType("text/event-stream");
		resp.setCharacterEncoding("UTF-8");
		
		AsyncContext asyncContext = request.startAsync();
		asyncContext.setTimeout(120*1000) ;
		
	    PrintWriter out = asyncContext.getResponse().getWriter();

	    // 正常返回数据
	    generatorOpenApi(asyncContext , out , request) ; 
	}

	private Flux<Object> generatorOpenApi(AsyncContext asyncContext, PrintWriter out, HttpServletRequest request) throws IOException {
		
		InputStream inputStream = request.getInputStream();
		String requestBody = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

		Flux<Object> flux = Flux.create(emitter -> {

			// TODO 处理任务并返回SSE信息

		});
		
		
		flux.subscribe(s -> {
			if(s != null && StringUtils.isNotBlank(s.toString())) {
		      out.println(s);
		      out.flush();
			}
		}, Throwable::printStackTrace, asyncContext::complete);

		return null;
	}

	private void generatorPaymentMessageInfo(AsyncContext asyncContext, PrintWriter out) {
		
		Flux<Object> flux = Flux.create(emitter -> {

			String payMessageInfo = "你的使用已经超过次数，请充值 [充值链接](#)" ; 
			
			emitter.next("") ;
			stopProcess(emitter , asyncContext) ;
			return;

		});
		
		flux.subscribe(s -> {
			if(s != null && StringUtils.isNotBlank(s.toString())) {
			  out.println(s);
			  out.flush();
			}
		}, Throwable::printStackTrace, asyncContext::complete);

	}

	private void generatorTokenMessageInfo(AsyncContext asyncContext, PrintWriter out) {
		Flux<Object> flux = Flux.create(emitter -> {

			String payMessageInfo = "账户Token异常，请点击左下角重置重新登陆." ; 
			
			emitter.next("") ;
			stopProcess(emitter , asyncContext) ;
			return;

		});
		
		flux.subscribe(s -> {
			if(s != null && StringUtils.isNotBlank(s.toString())) {
			  out.println(s);
			  out.flush();
			}
		}, Throwable::printStackTrace, asyncContext::complete);

	}

	/**
	 * 停止会话
	 */
	private void stopProcess(FluxSink<Object> emitter  , AsyncContext asyncContext) {
		log.info("OpenAI返回数据结束了");
		emitter.complete(); 
		asyncContext.complete();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}