package com.icefrog.terminal.mapper;

import com.icefrog.terminal.model.TbConfig;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TbConfigMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbConfig record);

    int insertSelective(TbConfig record);

    TbConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbConfig record);

    int updateByPrimaryKey(TbConfig record);
    
    TbConfig queryConfigByKey(@Param("key") String key);
}