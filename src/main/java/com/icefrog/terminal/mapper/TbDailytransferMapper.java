package com.icefrog.terminal.mapper;

import com.icefrog.terminal.model.TbDailytransfer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbDailytransferMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbDailytransfer record);

    int insertSelective(TbDailytransfer record);

    TbDailytransfer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbDailytransfer record);

    int updateByPrimaryKeyWithBLOBs(TbDailytransfer record);

    int updateByPrimaryKey(TbDailytransfer record);
    
    List<TbDailytransfer> pageQueryFer(@Param("status") String status, @Param("startTime") String startTime, @Param("endTime") String endTime);

    int logicRemove(@Param("id") String id);
}