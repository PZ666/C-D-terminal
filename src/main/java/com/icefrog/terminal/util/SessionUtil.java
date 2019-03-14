/*
 * Copyright 2019 icefrog.su@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.icefrog.terminal.util;

import com.icefrog.terminal.model.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * Session工具, 所有session的获取都应当从本类中支持的方法中获取
 * 便于分布式session操作
 *
 * @author icefrog.su@qq.com
 *
 */
public class SessionUtil {
	
	/***
	 * 获取session中当前登录的用户信息
	 * @param request HttpServletRequest request object
	 * @return TbUser object
	 */
	public static TbUser getSessionUser(HttpServletRequest request){
		return (TbUser) getAttribute(request, Constans.LOGINED_SESSION_USER);
	}
	
	/**
	 * 获取session,当不存在session时会强制创建一个新session并且返回
	 * @param request {@link HttpServletRequest}
	 * @return session {@link HttpSession}
	 */
	public static HttpSession getSession(final HttpServletRequest request){
		return request.getSession(true);
	}
	
	/**
	 * 获取session
	 * @param request {@link HttpServletRequest}
	 * @param create 当session获取为null时,指定是否强制创建一个新的session
	 * @return session {@link HttpSession}
	 */
	public static HttpSession getSession(final HttpServletRequest request, boolean create){
		return request.getSession(create);
	}
	
	/**
	 * 压入参数到session作用域中 所有session的入参必须调用此方法 以在分布式情况下同步session数据
	 * @param request {@link HttpServletRequest}
	 * @param key key
	 * @param value value
	 */
	public static void setAttribute(HttpServletRequest request, String key, Object value){
		getSession(request).setAttribute(key, value);
	}
	
	/**
	 * 从session中获取以key指定的结果. 如果key不存在或session失效,返回的结果可能为null.
	 * 本方法并非为强制约束调用
	 * @param request {@link HttpServletRequest}
	 * @param key 需要获取结果的key
	 * @return {@link Object}
	 */
	public static Object getAttribute(HttpServletRequest request, String key){
		return getSession(request).getAttribute(key);
	}
	
	/**
	 * 清除session中存储的所有key
	 * @param request {@link HttpServletRequest}
	 */
	public static void clearAll(HttpServletRequest request){
		HttpSession session = getSession(request);
		Enumeration<String> attributeNames = session.getAttributeNames();
		while(attributeNames.hasMoreElements()){
			String element = attributeNames.nextElement();
			session.setAttribute(element, null);
		}
	}
}
