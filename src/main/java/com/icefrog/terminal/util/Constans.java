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

/**
 * 为此层提供基础常量配置
 *
 * @author icefrog.su@qq.com
 *
 */
public class Constans {
	
	// 系统资源配置中关于企业编号配置ID
	public static final String CompanyID = "company_id";
	
	// 默认管理员编号
	public static final String DEFAULT_ADMIN_ID = "default_admin_id";
	
	public static final String LOGINED_SESSION_USER = "session_user";
	
	/**
	 * 500错误页
	 */
	public static final String ERROR_500 = "/error/500";
	
	/**
	 * 404错误页
	 */
	public static final String ERROR_404 = "/error/404";
	
	/**
	 * 406错误页
	 */
	public static final String ERROR_406 = "/error/406";
	
	/**
	 * 400错误页
	 */
	public static final String ERROR_400 = "/error/400";
	
	/**
	 * 访问受限页
	 */
	public static final String No_Permission = "/error/noprimers";
}
