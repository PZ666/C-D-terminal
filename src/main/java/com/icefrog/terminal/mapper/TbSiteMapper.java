package com.icefrog.terminal.mapper;

import com.icefrog.terminal.model.TbSite;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbSiteMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbSite record);

    int insertSelective(TbSite record);

    TbSite selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbSite record);

    int updateByPrimaryKey(TbSite record);
    
    int logicRemove(@Param("id") String id);
    
    List<TbSite> pageQuerySites(@Param("siteName") String siteName, @Param("siteCode") String siteCode, @Param("siteType") String siteType);
    
    List<TbSite> queryAllSites();
    
    int batchInsertSite(@Param("list") List<TbSite> list);
}