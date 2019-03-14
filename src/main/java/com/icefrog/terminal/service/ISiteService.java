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
