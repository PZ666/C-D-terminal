/*
 * Copyright 2018 www.zoomgo.net Co., Ltd 版权所有
 *
 * author: icefrog.su@qq.com
 *
 */

package com.icefrog.terminal.interceptor;

import com.icefrog.terminal.model.TbUser;
import com.icefrog.terminal.util.ApiResult;
import com.icefrog.terminal.util.SessionUtil;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


/**
 * 登录权限拦截器. 验证拦截规则内的所有请求是否登录. 否则跳转到登录页
 *
 * @author icefrog.su@qq.com
 *
 */
@Component
public class LoginedInterceptor implements HandlerInterceptor {
	
	private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(LoginedInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
        TbUser user = SessionUtil.getSessionUser(request);
        if(user == null){
			//未登录,跳转到登录页
			Logger.info(String.format("登录权限拦截器拦截到一次非登录请求[%s],转发到登录页!", request.getRequestURL()));
			
			//检查请求方案（不同的请求方案跳转登录的操作不同，如ajax请求无法直接进行页面跳转）
			String header = request.getHeader("X-Requested-With");
			if(header != null && header.equals("XMLHttpRequest")){
				//ajax请求
                ApiResult result = new ApiResult();
                result.setCode(99);
                result.setMessage("not login");
                PrintWriter out = response.getWriter();
                out.write(result.toJsonString());
                out.flush();
                out.close();
            }else{
				//非ajax请求时跳转登录的方案
				response.sendRedirect("/login.jsp");
			}
			return false;
		}else{
			return true;
		}
	}
}
