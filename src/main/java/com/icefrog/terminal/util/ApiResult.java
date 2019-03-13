/*
 * Copyright 2018 www.zoomgo.net Co., Ltd 版权所有
 *
 * author: icefrog.su@qq.com
 *
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