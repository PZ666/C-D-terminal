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
 * MD5加/解密
 *
 * @author icefrog.su@qq.com
 *
 */
public class MD5Utils {

	/**
	 * MD5加密
	 * @param password 待加密的明文
	 * @param salt 盐值
	 * @return 加密后的密文
	 */
	public static String MD5Encoder(String password,String salt){
		return MD5.MD5Encode(salt + MD5.MD5Encode(password, "UTF-8"), "UTF-8");
	}
	
	/**
	 * MD5比对
	 * @param md5 密文
	 * @param salt 盐值
	 * @param compValue 明文
	 * @return 如果比对成功,返回true, 否则返回false
	 */
	public static boolean MD5Comparator(String md5,String salt,String compValue){
		return MD5Encoder(compValue, salt).equals(md5);
	}
}