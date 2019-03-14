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

import net.sf.json.JSONObject;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApiResult {
	
	public static final Integer CODE_SUCCESS        = 0;
	public static final Integer CODE_FAILED         = 1;
	public static final Integer CODE_INVALID        = 2;
	public static final Integer CODE_WEEKEND        = 3;
	public static final String  DEFAULT_FUNCTIONID  = "default_function_id";
	public static final String  SUCCESS_MESSAGE     = "success";
	public static final String  FAILED_MESSAGE      = "failed";
	
	private String functionID;
	
	private String message;
	
	private Integer code;
	
	private Map<String, Object> data = new ConcurrentHashMap<String, Object>();
	
	/**
	 * Use net.sf.json-lib convert object for this to json string.<br>
	 * <code>
	 * 	net.sf.json.JSONObject.fromObject(this).toString()
	 * <code>
	 */
	public String toJsonString(){
		return JSONObject.fromObject(this).toString();
	}
	
	
	public String getFunctionID() {
		return functionID;
	}

	public ApiResult setFunctionID(String functionID) {
		this.functionID = functionID;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public ApiResult setMessage(String message) {
		this.message = message;
		return this;
	}

	public Integer getCode() {
		return code;
	}

	public ApiResult setCode(Integer code) {
		this.code = code;
		return this;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public ApiResult setData(Map<String, Object> data) {
		this.data = data;
		return this;
	}
	
	public Map<String, Object> putDate(String key,Object val){
		this.data.put(key, val);
		return this.data;
	}
}