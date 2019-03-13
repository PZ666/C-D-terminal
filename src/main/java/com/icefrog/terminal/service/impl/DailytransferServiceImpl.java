/*
 * Copyright 2019 版权所有
 *
 * @since 1.0
 * @author: icefrog.su@qq.com
 */

package com.icefrog.terminal.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icefrog.terminal.mapper.TbDailytransferMapper;
import com.icefrog.terminal.mapper.TbDailytransferOrderMapper;
import com.icefrog.terminal.mapper.TbProductTypeMapper;
import com.icefrog.terminal.model.TbDailytransfer;
import com.icefrog.terminal.model.TbDailytransferOrder;
import com.icefrog.terminal.model.TbProductType;
import com.icefrog.terminal.model.TbSite;
import com.icefrog.terminal.service.IConfigService;
import com.icefrog.terminal.service.IDailytransferService;
import com.icefrog.terminal.service.ISiteService;
import com.icefrog.terminal.util.*;
import com.icefrog.terminal.vo.OrderPointer;
import com.icefrog.terminal.webservice.PushUtils;
import com.icefrog.terminal.webservice.dto.ServiceStationCommodityDto;
import com.icefrog.terminal.webservice.dto.ServiceStationDto;
import com.icefrog.terminal.webservice.dto.ServiceStationReportDto;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DailytransferServiceImpl extends BaseService implements IDailytransferService {
    
    @Autowired
    private TbDailytransferMapper dailytransferMapper;
    
    @Autowired
    private TbDailytransferOrderMapper dailytransferOrderMapper;
    
    @Autowired
    private IConfigService configService;
    
    @Autowired
    private ISiteService siteService;
    
    @Autowired
    private TbProductTypeMapper productTypeMapper;
    
    
    @Transactional
    @Override
    public ApiResult save(String jsonStr, String userId, String fn) throws ParseException {
    
        System.out.println(jsonStr);
    
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        JSONArray orderInfo = JSONArray.fromObject(jsonObject.get("orderInfo"));
        JSONArray siteIdOrderCount = JSONArray.fromObject(jsonObject.get("siteIdOrderCount"));
    
        System.out.println("orderInfo:"+orderInfo.toString());
        System.out.println("siteIdOrderCount:"+siteIdOrderCount.toString());
    
        // 保存数据上传主体数据
        TbDailytransfer dailytransfer = new TbDailytransfer();
        String dailytransferId = IDGenerate.buildID();
        dailytransfer.setId(dailytransferId);
        dailytransfer.setCreateId(userId);
        dailytransfer.setUpdateId(userId);
        dailytransfer.setCreateTime(new Date());
        dailytransfer.setUpdateTime(new Date());
        dailytransfer.setReportTime(new SimpleDateFormat("yyyy/MM/dd").parse(jsonObject.getString("reportDate")));
        dailytransfer.setStatus(0);// 0:待传输，1:传输成功, 2:传输失败
        dailytransfer.setCompanyName("");
        String companyId = configService.getValue(Constans.CompanyID);
        if(StringUtils.isEmpty(companyId)){
            return error(fn, "请先位于系统配置中配置企业编码!");
        }
        dailytransfer.setCompanyId(companyId);
        dailytransfer.setIsDel(0);
        int resultWithDailytransfer = dailytransferMapper.insertSelective(dailytransfer);
        if(resultWithDailytransfer <= 0){
            return error(fn, "保存数据上传主体数据失败,数据库未受影响!");
        }
    
        // 保存数据上传分支数据（订单相关详情）
        for(int i = 0; i < orderInfo.size(); i++){
            JSONObject json = JSONObject.fromObject(orderInfo.get(i));
            String siteCode = json.getString("siteCode");
            String buySellType = json.getString("buySellType");
            String productType = json.getString("productType");
            String sumMoney = json.getString("sumMoney");
    
            TbDailytransferOrder order = new TbDailytransferOrder();
            order.setId(IDGenerate.buildID());
            order.setSiteId(siteCode);
            order.setProductTypeId(productType);
            order.setDailytransferId(dailytransferId);
            order.setBuySellType(buySellType);
            order.setMoney(sumMoney);
            Map<String, Integer> orderCount = getOrderCount(siteIdOrderCount, siteCode);
            order.setBuyCount(orderCount.get("buyCount"));
            order.setSellCount(orderCount.get("sellCount"));
            order.setIsDel(0);
            int orderResult = dailytransferOrderMapper.insertSelective(order);
            if(orderResult <= 0){
                throw new RuntimeException("保存订单相关数据时失败,数据库无数据受影响!");
            }
        }
    
        return success(fn);
    }
    
    @Transactional
    @Override
    public ApiResult update(String dailytransferId, String jsonStr, String userId, String fn) throws ParseException {
    
        System.out.println(jsonStr);
        TbDailytransfer dailytransfer = dailytransferMapper.selectByPrimaryKey(dailytransferId);
        if(dailytransfer == null){
            return error(fn, "未知数据上传对象, 请刷新页面重试!");
        }
    
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        
        dailytransfer.setUpdateId(userId);
        dailytransfer.setUpdateTime(new Date());
        dailytransfer.setReportTime(new SimpleDateFormat("yyyy/MM/dd").parse(jsonObject.getString("reportDate")));
        int updateDailytransferResult = dailytransferMapper.updateByPrimaryKeySelective(dailytransfer);
        if(updateDailytransferResult <= 0){
            return error(fn, "修改传输主体失败,无数据受影响!");
        }
        
        // 修改对应订单数据
        JSONArray jsonArray = jsonObject.getJSONArray("orders");
        List<TbDailytransferOrder> orders = dailytransferOrderMapper.queryAllByDailytransferId(dailytransferId);
        for (TbDailytransferOrder order : orders) {
            OrderPointer updateOrder = OrderPointer.pointer(order.getId(), jsonArray);
            if(updateOrder == null){
                continue;
            }
            order.setMoney(updateOrder.getSumMoney());
            order.setBuyCount(updateOrder.getBuyCount());
            order.setSellCount(updateOrder.getSellCount());
            // 更新到数据库
            dailytransferOrderMapper.updateByPrimaryKeySelective(order);
        }
        return success(fn);
    }
    
    @Override
    public ApiResult pageQuery(String pageIndex, String pageSize, String status, String startTime, String endTime, String fn) {
        
        if(StringUtils.isEmpty(status) || "-1".equals(status)){
            status = null;
        }
        if(StringUtils.isEmpty(startTime)){
            startTime = null;
        }else{
            startTime = startTime + " 00:00:00";
        }
        if(StringUtils.isEmpty(endTime)){
            endTime = null;
        }else{
            endTime = endTime + " 23:59:59";
        }
    
        PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        List<TbDailytransfer> list = dailytransferMapper.pageQueryFer(status, startTime, endTime);
        PageInfo<TbDailytransfer> info = new PageInfo<>(list);
    
        return success(fn, ApiResult.SUCCESS_MESSAGE, "list", info);
    }
    
    @Transactional
    @Override
    public ApiResult batchRemove(String ids, String fn) {
        if(StringUtils.isEmpty(ids)){
            return success(fn, "未删除任何数据!");
        }
        String[] idArray = ids.split(",");
        int count = 0;
        for (String id : idArray) {
            dailytransferMapper.logicRemove(id);
            count += 1;
        }
        return success(fn, String.format("删除成功,本次共删除%d条数据!",count));
    }
    
    @Override
    public TbDailytransfer selectByPrimaryKey(String id) {
        return dailytransferMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public List<TbDailytransferOrder> queryAllByDailytransferId(String dailytransferId) {
        return dailytransferOrderMapper.queryAllByDailytransferId(dailytransferId);
    }
    
    private Map<String, Integer> getOrderCount(JSONArray array, String siteId){
        Map<String, Integer> data = new HashMap<>();
        for(int i = 0; i<array.size(); i++){
            JSONObject json = JSONObject.fromObject(array.get(i));
            if(siteId.equals(json.getString("siteId"))){
                String buyCount = json.getString("buyCount");
                String sellCount = json.getString("sellCount");
                data.put("buyCount", Integer.parseInt(buyCount));
                data.put("sellCount", Integer.parseInt(sellCount));
            }
        }
        return data;
    }
    
    /**
     * 通过webservice上传数据到商务部
     * @param id dailytransfer id
     * @param fn function id
     * @return ApiResult
     */
    @Override
    public ApiResult Upstream(String id, String fn) throws RemoteException {
    
        TbDailytransfer dailytransfer = dailytransferMapper.selectByPrimaryKey(id);
        List<TbDailytransferOrder> orders = dailytransferOrderMapper.queryAllByDailytransferId(id);
        dailytransfer.setOrders(orders);
    
        ServiceStationDto serviceStationDto = convertToUpstreamDto(dailytransfer);
        String pushResult = PushUtils.push(serviceStationDto);
        System.out.println(pushResult);
        
        // 修改数据状态
        dailytransfer.setStatus(1);//传输状态,0:待传输，1:传输成功, 2:传输失败
        dailytransfer.setXmlData(pushResult);
        dailytransferMapper.updateByPrimaryKeyWithBLOBs(dailytransfer);
        return success(fn);
    }
    
    // 整理数据上传工具类所需DTO资源
    private ServiceStationDto convertToUpstreamDto(TbDailytransfer dailytransfer){
    
        // WebService xml文档根
        ServiceStationDto masterDto = new ServiceStationDto();
        // 读取全局资源配置中的企业编码(userId)
        String companyId = configService.getValue(Constans.CompanyID);
        masterDto.setUserId(companyId);
        masterDto.setRptDate(new SimpleDateFormat("yyyy-MM-dd").format(dailytransfer.getReportTime()));
    
        // 各站点交易信息列表
        List<ServiceStationReportDto> serviceStationReports = new ArrayList<>();
        for (TbDailytransferOrder order : dailytransfer.getOrders()) {
            TbSite site = siteService.selectByPrimaryKey(order.getSiteId());
            if(isExsitsSite(serviceStationReports, site.getSiteCode())){
                // 已经包含此站点,去重
                continue;
            }
            ServiceStationReportDto report = new ServiceStationReportDto();
            report.setCode(site.getSiteCode());
            report.setName(site.getSiteName());
            report.setCountyType(site.getSiteType());
            report.setBuyOrder(String.valueOf(order.getBuyCount()));
            report.setSaleOrder(String.valueOf(order.getSellCount()));
    
            // 商品信息列表
            List<ServiceStationCommodityDto> serviceStationCommoditys = buildServiceStationCommoditys(order.getSiteId(), dailytransfer.getId());
            
            report.setServiceStationCommoditys(serviceStationCommoditys);
    
            serviceStationReports.add(report);
        }
    
        masterDto.setServiceStationReports(serviceStationReports);
        
        return masterDto;
    }
    
    private List<ServiceStationCommodityDto> buildServiceStationCommoditys(String siteId, String dailytransferId){
        List<ServiceStationCommodityDto> list = new ArrayList<>();
        List<TbDailytransferOrder> ordersBySites = dailytransferOrderMapper.queryAllOrdersBySiteId(siteId, dailytransferId);
        for (TbDailytransferOrder order : ordersBySites) {
    
            TbProductType productType = productTypeMapper.selectByPrimaryKey(order.getProductTypeId());
            String commId;
            if("代卖".equals(order.getBuySellType())){
                commId = productType.getGenerateSellNo();
            }else{
                commId = productType.getGenerateBuyNo();
            }
            boolean isExists = false;
            for (ServiceStationCommodityDto dto : list) {
                if(dto.getCommId().equals(commId)){
                    // 已经存在的商品类别， 累加金额
                    double beforeMoney = Double.parseDouble(dto.getMoney());
                    double nowMoney = Double.parseDouble(order.getMoney());
                    dto.setMoney(String.valueOf(beforeMoney + nowMoney));
                    isExists = true;
                }
            }
            if(!isExists){
                ServiceStationCommodityDto commodity = new ServiceStationCommodityDto();
                commodity.setCommId(commId);
                commodity.setMoney(order.getMoney());
                list.add(commodity);
            }
        }
        return list;
    }
    
    private boolean isExsitsSite(List<ServiceStationReportDto> serviceStationReports, String siteCode){
        for (ServiceStationReportDto item : serviceStationReports) {
            if(siteCode.equals(item.getCode())){
                return true;
            }
        }
        return false;
    }
}
