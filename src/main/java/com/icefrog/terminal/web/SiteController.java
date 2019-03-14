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

import com.icefrog.terminal.model.TbSite;
import com.icefrog.terminal.service.ISiteService;
import com.icefrog.terminal.util.ApiBaseController;
import com.icefrog.terminal.util.ApiResult;
import com.icefrog.terminal.util.ExcelUtils;
import com.icefrog.terminal.util.SessionUtil;
import com.icefrog.terminal.vo.SiteImportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequestMapping("/site")
@Controller
public class SiteController extends ApiBaseController {

    @Autowired
    private ISiteService siteService;
    
    @RequestMapping("/toAddSite")
    public String toAddSite(){
        return "site/site-add";
    }
    
    @RequestMapping("/toSites")
    public String toSites(){
        return "site/site-list";
    }
    
    @RequestMapping("/toViewSite")
    public String toViewSite(@RequestParam(name = "id", required = false) String id, HttpServletRequest request){
        TbSite site = siteService.selectByPrimaryKey(id);
        request.setAttribute("site", site);
        return "site/site-view";
    }
    
    @RequestMapping("/toEditSite")
    public String toEditSite(@RequestParam(name = "id", required = false) String id, HttpServletRequest request){
        TbSite site = siteService.selectByPrimaryKey(id);
        request.setAttribute("site", site);
        return "site/site-edit";
    }
    
    @RequestMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        super.downloadTemplate(response, request, "site-import.xls");
    }
    
    @RequestMapping("/importSites")
    @ResponseBody
    public ApiResult importSites(@RequestPart(name = "siteFile", required = false) MultipartFile siteFile,
                                 HttpServletRequest request) throws IOException {
        String fn = "/site/importSites";
        List<SiteImportDto> sites = (List<SiteImportDto>) ExcelUtils.importExcel(siteFile.getInputStream(), 1, 0, SiteImportDto.class);
        return siteService.importSite(sites, SessionUtil.getSessionUser(request).getId(), fn);
    }
    
    @RequestMapping("/initSites")
    @ResponseBody
    public ApiResult initSites(@RequestParam(name = "pageIndex", required = false) String pageIndex,
                               @RequestParam(name = "pageSize", required = false) String pageSize,
                               @RequestParam(name = "siteName", required = false) String siteName,
                               @RequestParam(name = "siteCode", required = false) String siteCode,
                               @RequestParam(name = "siteType", required = false) String siteType){
        return siteService.pageQuerySites(pageIndex, pageSize, siteName, siteCode, siteType, "/site/initSites");
    }
    
    @RequestMapping("/save")
    @ResponseBody
    public ApiResult save(@RequestParam(name="siteType", required = false) String siteType,
                          @RequestParam(name="siteName", required = false) String siteName,
                          @RequestParam(name="siteCode", required = false) String siteCode,
                          HttpServletRequest request){
        return siteService.save(siteType, siteName, siteCode, SessionUtil.getSessionUser(request).getId(), "");
    }
    
    @RequestMapping("/doEditSite")
    @ResponseBody
    public ApiResult doEdit(@RequestParam(name = "siteType", required = false) String siteType,
                            @RequestParam(name = "siteName", required = false) String siteName,
                            @RequestParam(name = "siteCode", required = false) String siteCode,
                            @RequestParam(name = "id", required = false) String id,
                            HttpServletRequest request){
        return siteService.update(id, siteType, siteName, siteCode, SessionUtil.getSessionUser(request).getId(),"/site/doEditSite");
    }
    
    @RequestMapping("/batchRemove")
    @ResponseBody
    public ApiResult batchRemove(@RequestParam(name = "ids", required = false) String ids){
        return siteService.batchRemove(ids, "/site/batchRemove");
    }
    
}
