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