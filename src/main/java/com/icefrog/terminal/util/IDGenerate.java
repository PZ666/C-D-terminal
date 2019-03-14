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

import java.util.Random;
import java.util.UUID;

public class IDGenerate {
	
	public synchronized static final String buildID() {
		synchronized (IDGenerate.class) {
			return UUID.randomUUID().toString().replaceAll("-", "");
		}
	}
	
	/**
	 * 六位随机数值短信验证码生成器
	 *
	 * @author icefrog.su@qq.com
	 *
	 */
	public synchronized static String randomSMSCode() {
		Random rad = new Random();
		String result = rad.nextInt(1000000) + "";
		if (result.length() != 6) {
			return randomSMSCode();
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(randomSMSCode());
	}
	
	/**
	 * 生成按钮唯一编码
	 * @return
	 */
	public static String buildButtonUniqueCode(){
		String salt = String.valueOf(System.currentTimeMillis());
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid+salt;
	}
	
	/**
	 * 游客注册时生成随机用户名
	 * @return {@link String} 随机用户名
	 */
	public static String generateRandomName(){
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 5);
	}
}