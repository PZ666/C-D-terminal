/*
 * Copyright 2019 版权所有
 *
 * @since 1.0
 * @author: icefrog.su@qq.com
 */

package com.icefrog.terminal.web;

import com.icefrog.terminal.service.IConfigService;
import com.icefrog.terminal.util.ApiBaseController;
import com.icefrog.terminal.util.ApiResult;
import com.icefrog.terminal.util.Constans;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/***
 * 系统资源配置controller
 *
 * @author icefrog.su@qq.com
 */
@RequestMapping("/config")
@Controller
public class ConfigController extends ApiBaseController {
    
    @Autowired
    private IConfigService configService;
    
    @RequestMapping("/toConfig")
    public String toConfig(HttpServletRequest request){
        request.setAttribute("companyId", configService.getValue(Constans.CompanyID));
        return "config/config";
    }
    
    @RequestMapping("/doSave")
    @ResponseBody
    public ApiResult doSave(HttpServletRequest request){
        String companyId = request.getParameter("companyId");
        if(StringUtils.isNotEmpty(companyId)){
            companyId = companyId.trim();
        }
        return configService.setValue(Constans.CompanyID, companyId);
    }
}
