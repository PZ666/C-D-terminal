/*
 * Copyright 2019 版权所有
 *
 * @since 1.0
 * @author: icefrog.su@qq.com
 */

package com.icefrog.terminal.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system")
public class SystemController {
    
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
    
    @RequestMapping("/toIndex")
    public String toIndex(){
        return "index";
    }
    
    @RequestMapping("/toSites")
    public String toSites(){
        return this.toIndex();
    }
    
    @RequestMapping("/toUploadLogs")
    public String toUploadLogs(){
        return "";
    }
    
    @RequestMapping("/cancelLogin")
    public String cancelLogin(){
        return this.toLogin();
    }
    
    @RequestMapping("/toAbout")
    public String toAbout(){
        return "about";
    }
    
}
