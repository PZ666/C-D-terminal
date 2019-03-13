/*
 * Copyright 2018 www.zoomgo.net Co., Ltd 版权所有
 *
 * author: icefrog.su@qq.com
 *
 */

package com.icefrog.terminal.util;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ApiBaseController extends BaseController {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ApiBaseController.class);

	/**
	 * Spring全局异常处理器. 
	 * 
	 * @author icefrog.su@qq.com
	 * @throws IOException 
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public void exceptionHandler(HttpServletRequest request, HttpServletResponse response , Exception ex) throws IOException {
		LOGGER.error(ex.getMessage(), ex);
		ex.printStackTrace();
		//检查本次请求的问题原因
		String header = request.getHeader("X-Requested-With");
		if(header != null && header.equals("XMLHttpRequest")){
			ApiResult result = new ApiResult();
			result.setCode(ApiResult.CODE_FAILED);
			result.setMessage(ex.getMessage());
			//ajax请求
			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();   
			out.print(result.toJsonString());
			out.flush();   
			out.close();
		}else{
			//非ajax请求时直接跳转到错误页
			response.sendRedirect("/common/500");
		}
	}
	
	protected HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest();
    }
    
    protected HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getResponse();
    }
 
}
