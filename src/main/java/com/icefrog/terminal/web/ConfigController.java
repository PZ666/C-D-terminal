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
