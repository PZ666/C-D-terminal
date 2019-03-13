/*
 * Copyright 2019 版权所有
 *
 * @since 1.0
 * @author: icefrog.su@qq.com
 */

package com.icefrog.terminal.web;

import com.icefrog.terminal.model.TbDailytransfer;
import com.icefrog.terminal.model.TbDailytransferOrder;
import com.icefrog.terminal.model.TbProductType;
import com.icefrog.terminal.model.TbSite;
import com.icefrog.terminal.service.IDailytransferService;
import com.icefrog.terminal.service.IProductTypeService;
import com.icefrog.terminal.service.ISiteService;
import com.icefrog.terminal.util.ApiBaseController;
import com.icefrog.terminal.util.ApiResult;
import com.icefrog.terminal.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.List;

/***
 * 数据传输controller
 *
 */
@Controller
@RequestMapping("/dailytransfer")
public class DailytransferController extends ApiBaseController {
    
    @Autowired
    private ISiteService siteService;
    
    @Autowired
    private IProductTypeService productTypeService;
    
    @Autowired
    private IDailytransferService dailytransferService;
    
    @RequestMapping("/toAddDailytransfer")
    public String toAddDailytransfer(HttpServletRequest request){
        List<TbSite> sites = siteService.queryAllSites();
        request.setAttribute("sites", sites);
        List<TbProductType> productTypes = productTypeService.queryAllProductTypes();
        request.setAttribute("productTypes", productTypes);
        return "dailytransfer/dailytransfer-add";
    }
    
    @RequestMapping("/list")
    public String list(){
        return "dailytransfer/dailytransfer-list";
    }
    
    @RequestMapping("/toView")
    public String toView(@RequestParam(name = "id", required = false) String id,
                         HttpServletRequest request){
        List<TbSite> sites = siteService.queryAllSites();
        request.setAttribute("sites", sites);
    
        TbDailytransfer dailytransfer = dailytransferService.selectByPrimaryKey(id);
        List<TbDailytransferOrder> orders = dailytransferService.queryAllByDailytransferId(dailytransfer.getId());
        dailytransfer.setOrders(orders);
        request.setAttribute("dailytransfer", dailytransfer);
    
        return "dailytransfer/dailytransfer-view";
    }
    
    @RequestMapping("/toEdit")
    public String toEdit(@RequestParam(name = "id", required = false) String id,
                         HttpServletRequest request){
        List<TbSite> sites = siteService.queryAllSites();
        request.setAttribute("sites", sites);
    
        TbDailytransfer dailytransfer = dailytransferService.selectByPrimaryKey(id);
        List<TbDailytransferOrder> orders = dailytransferService.queryAllByDailytransferId(dailytransfer.getId());
        dailytransfer.setOrders(orders);
        request.setAttribute("dailytransfer", dailytransfer);
        return "dailytransfer/dailytransfer-edit";
    }
    
    @RequestMapping("/initFers")
    @ResponseBody
    public ApiResult initFers(@RequestParam(name = "pageIndex", required = false) String pageIndex,
                       @RequestParam(name = "pageSize", required = false) String pageSize,
                       @RequestParam(name = "status", required = false) String status,
                       @RequestParam(name = "startTime", required = false) String startTime,
                       @RequestParam(name = "endTime", required = false) String endTime){
        return dailytransferService.pageQuery(pageIndex, pageSize, status, startTime, endTime, "/dailytransfer/initFers");
    }
    
    @RequestMapping("/save")
    @ResponseBody
    public ApiResult save(@RequestParam(name = "jsonStr", required = false) String jsonStr,
                          HttpServletRequest request) throws ParseException {
        return dailytransferService.save(jsonStr, SessionUtil.getSessionUser(request).getId(), "/dailytransfer/save");
    }
    
    @RequestMapping("/update")
    @ResponseBody
    public ApiResult update(@RequestParam(name = "jsonStr", required = false) String jsonStr,
                            @RequestParam(name = "dailytransferId", required = false) String dailytransferId,
                            HttpServletRequest request) throws ParseException {
        return dailytransferService.update(dailytransferId,
                                          jsonStr,
                                          SessionUtil.getSessionUser(request).getId(),
                                          "/dailytransfer/update");
    }
    
    @RequestMapping("/batchRemove")
    @ResponseBody
    public ApiResult batchRemove(@RequestParam(name = "ids", required = false) String ids){
        return dailytransferService.batchRemove(ids, "/dailytransfer/batchRemove");
    }
    
    @RequestMapping("/uploadToEc")
    @ResponseBody
    public ApiResult uploadToEc(@RequestParam(name = "id", required = false) String id) throws RemoteException {
        String fn = "/dailytransfer/uploadToEc";
        return dailytransferService.Upstream(id, fn);
    }
}
