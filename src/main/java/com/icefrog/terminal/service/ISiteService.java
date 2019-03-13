/*
 * Copyright 2019 版权所有
 *
 * @since 1.0
 * @author: icefrog.su@qq.com
 */

package com.icefrog.terminal.service;

import com.icefrog.terminal.model.TbSite;
import com.icefrog.terminal.util.ApiResult;
import com.icefrog.terminal.vo.SiteImportDto;

import java.util.List;

public interface ISiteService {
    
    TbSite selectByPrimaryKey(String id);
    
    int updateByPrimaryKeySelective(TbSite site);
    
    int insertSelective(TbSite site);
    
    ApiResult save(String siteType, String siteName, String siteCode, String userId, String fn);
    
    ApiResult update(String id, String siteType, String siteName, String siteCode, String userId, String fn);
    
    ApiResult pageQuerySites(String pageIndex, String pageSize, String siteName, String siteCode, String siteType, String fn);
    
    ApiResult batchRemove(String ids, String fn);
    
    List<TbSite> queryAllSites();
    
    ApiResult importSite(List<SiteImportDto> sites, String userId, String fn);
    
}
