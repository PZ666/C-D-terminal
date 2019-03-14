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

package com.icefrog.terminal.web;

import com.icefrog.terminal.mapper.TbUserMapper;
import com.icefrog.terminal.model.TbUser;
import com.icefrog.terminal.util.*;
import com.icefrog.terminal.vo.Menu;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 系统Controller。 本controller下所有方法不被任何拦截器拦截(包括登录权限验证拦截器).
 *
 * @author icefrog.su@qq.com
 *
 */
@Controller
@RequestMapping("/system")
public class SystemController extends ApiBaseController {
	
	private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(SystemController.class);
	
	@Autowired
	private TbUserMapper userMapper;
	
	/**
	 * 登录Controller method. 登录返回json字符串 包含是否成功以及各种失败状态
	 * 必须使用POST方式提交
	 */
	@PostMapping("/login")
	@ResponseBody
	public ApiResult doLogin(HttpServletRequest request) {
		
		String fn = "/system/login";
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String code = request.getParameter("code");
		
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(code)) {
			return error(fn, "用户名或密码和验证码不能为空!");
		}
		
		username = username.trim();
		
		Object obj = SessionUtil.getAttribute(request, "vrifyCode");
		if (obj == null || !obj.toString().equalsIgnoreCase(code)) {
			return custome(fn, "验证码不正确!", 2);
		}
		Object buildedTime = SessionUtil.getAttribute(request, codeTime);
		if (obj == null || StringUtils.isBlank(obj.toString()) || buildedTime == null || StringUtils.isBlank(buildedTime.toString())) {
			return custome(fn, "请点击获取新的验证码!", 2);
		}
		// 检查验证码是否失效
		Date now = new Date();
		if ((now.getTime() - Long.valueOf(buildedTime.toString())) / 1000 / 60 > 1) {
			// 验证码有效时间已经超过1分钟
			return custome(fn, "验证码已失效,请点击获取新的验证码!", 2);
		}
		
		TbUser user = userMapper.queryUserByMobile(username);
		if (user == null) {
			return error(fn, "用户不存在!");
		}
		//MD5比对密码是否正确
		if (!MD5Utils.MD5Comparator(user.getPassword(), user.getSalt(), password)) {
			return error(fn, "用户名或密码错误!");
		}
		
		Logger.info(String.format("用户【%s】登录成功! 用户ID【%s】 ,当前时间【%s】", user.getUserName(), user.getId(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
		
		//1. 用户信息保存到session
		SessionUtil.setAttribute(request, Constans.LOGINED_SESSION_USER, user);
		
		SessionUtil.setAttribute(request, "menus", buildMenuList());
		
		//6.登录成功回调地址
		Map<String, Object> data = new HashMap<>();
		data.put("callback", "/system/portal");
		
		return success(fn, "登录成功", data);
	}
	
	@RequestMapping("/portal")
	public String portal(){
		return "framework/main";
	}
	
	@RequestMapping("/initLeft")
	public String initLeft(){
		return "framework/left";
	}
	
	@RequestMapping("/toIndex")
	public String toIndex(){
		return "index";
	}
	
	List<Menu> buildMenuList(){
		
		List<Menu> menus = new LinkedList<>();
		
		menus.add(buildMenu("站点录入", "/site/toSites", "/menu-icons/site.png"));
		menus.add(buildMenu("商品类型", "/product-type/toProductTypes", "/menu-icons/goods.png"));
		menus.add(buildMenu("资源配置", "/config/toConfig", "/menu-icons/goods.png"));
		menus.add(buildMenu("数据传输", "/dailytransfer/list", "/menu-icons/goods.png"));
		
		return menus;
	}
	
	
	Menu buildMenu(String menuName, String url, String iconUrl){
		Menu menu = new Menu();
		menu.setMenuname(menuName);
		menu.setUrl(url);
		menu.setIconurl(iconUrl);
		return menu;
	}
	
	
	/**
	 * 注销登录
	 *
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SessionUtil.setAttribute(request, Constans.LOGINED_SESSION_USER, null);
		response.sendRedirect("/login.jsp");
	}
	
	
	String simpleCaptcha = "vrifyCode";
	String codeTime = "codeTime";
	
	/**
	 * 验证码生成
	 *
	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @throws Exception
	 */
	@RequestMapping("/authcode")
	public void authcode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = SessionUtil.getSession(request);
		OutputStream os = response.getOutputStream();
		Map<String, Object> map = ImageCodeBuilder.getImageCode(60, 20, os);
		session.setAttribute(simpleCaptcha, map.get("strEnsure").toString().toLowerCase());
		session.setAttribute(codeTime, new Date().getTime());
		try {
			ImageIO.write((BufferedImage) map.get("image"), "JPEG", os);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
