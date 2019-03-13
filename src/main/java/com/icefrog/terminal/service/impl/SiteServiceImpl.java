/*
 * Copyright 2019 版权所有
 *
 * @since 1.0
 * @author: icefrog.su@qq.com
 */

package com.icefrog.terminal.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icefrog.terminal.mapper.TbSiteMapper;
import com.icefrog.terminal.model.TbSite;
import com.icefrog.terminal.service.ISiteService;
import com.icefrog.terminal.util.ApiResult;
import com.icefrog.terminal.util.BaseService;
import com.icefrog.terminal.util.IDGenerate;
import com.icefrog.terminal.vo.SiteImportDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SiteServiceImpl extends BaseService implements ISiteService {
    
    @Autowired
    private TbSiteMapper siteMapper;
    
    @Override
    public TbSite selectByPrimaryKey(String id) {
        return siteMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public int updateByPrimaryKeySelective(TbSite site) {
        return siteMapper.updateByPrimaryKeySelective(site);
    }
    
    @Override
    public int insertSelective(TbSite site) {
        return siteMapper.insertSelective(site);
    }
    
    @Override
    public ApiResult save(String siteType, String siteName, String siteCode, String userId, String fn) {
        
        TbSite site = new TbSite();
        site.setId(IDGenerate.buildID());
        site.setCreateId(userId);
        site.setUpdateId(userId);
        site.setCreateTime(new Date());
        site.setUpdateTime(new Date());
        site.setSiteName(siteName);
        site.setSiteType(siteType);
        site.setSiteCode(siteCode);
        site.setIsDel(0);
    
        int result = this.insertSelective(site);
    
        return result > 0 ? success(fn) : error(fn);
    }
    
    @Override
    public ApiResult update(String id, String siteType, String siteName, String siteCode, String userId, String fn) {
        TbSite site = new TbSite();
        site.setId(id);
        site.setUpdateId(userId);
        site.setUpdateTime(new Date());
        site.setSiteName(siteName);
        site.setSiteType(siteType);
        site.setSiteCode(siteCode);
        site.setIsDel(0);
    
        int result = this.updateByPrimaryKeySelective(site);
    
        return result > 0 ? success(fn) : error(fn);
    }
    
    @Override
    public ApiResult pageQuerySites(String pageIndex, String pageSize, String siteName, String siteCode, String siteType, String fn) {
        
        if(StringUtils.isEmpty(siteName)){
            siteName = null;
        }else{
            siteName = "%" + siteName + "%";
        }
        if(StringUtils.isEmpty(siteCode)){
            siteCode = null;
        }else{
            siteCode = "%" + siteCode + "%";
        }
        if(StringUtils.isEmpty(siteType) || "-1".equals(siteType)){
            siteType = null;
        }
    
        PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        List<TbSite> sites = siteMapper.pageQuerySites(siteName, siteCode, siteType);
        PageInfo<TbSite> info = new PageInfo<>(sites);
    
        return success(fn, ApiResult.SUCCESS_MESSAGE, "sites", info);
    }
    
    @Override
    public ApiResult batchRemove(String ids, String fn) {
        if(StringUtils.isEmpty(ids)){
            return error(fn, "未删除任何数据!");
        }
        String[] idArray = ids.split(",");
        int count = 0;
        for (String id : idArray) {
            siteMapper.logicRemove(id);
            count++;
        }
        return success(fn, String.format("删除成功, 本次共删除%d条数据!",count));
    }
    
    @Override
    public List<TbSite> queryAllSites() {
        return siteMapper.queryAllSites();
    }
    
    @Transactional
    @Override
    public ApiResult importSite(List<SiteImportDto> sites, String userId, String fn) {
    
        // 对导入的所有数据进行校验和替换成数据库模型
        List<TbSite> siteModels = new ArrayList<>();
        for (SiteImportDto site : sites) {
            TbSite s = new TbSite();
            s.setId(IDGenerate.buildID());
            s.setCreateId(userId);
            s.setUpdateId(userId);
            s.setCreateTime(new Date());
            s.setUpdateTime(new Date());
            s.setSiteName(site.getSiteName());
            s.setSiteCode(site.getSiteCode());
            switch (site.getSiteType()){
                case "村级服务站":
                    s.setSiteType("3");
                    break;
                case "乡镇级服务站":
                    s.setSiteType("2");
                    break;
                case "县级服务中心":
                    s.setSiteType("1");
                    break;
                default:
                    s.setSiteType("-1");// 标识未知
                    break;
            }
            s.setSiteType(s.getSiteType());
            s.setRes1(null);
            s.setRes2(null);
            s.setRes3(null);
            s.setIsDel(0);
            siteModels.add(s);
        }
        int result = siteMapper.batchInsertSite(siteModels);
        System.out.println(result);
        return success(fn, String.format("导入成功,本次共导入%d条数据", result));
    }
}
