package com.icefrog.terminal.mapper;

import com.icefrog.terminal.model.TbDailytransferOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbDailytransferOrderMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbDailytransferOrder record);

    int insertSelective(TbDailytransferOrder record);

    TbDailytransferOrder selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbDailytransferOrder record);

    int updateByPrimaryKey(TbDailytransferOrder record);
    
    List<TbDailytransferOrder> queryAllByDailytransferId(@Param("dailytransferId") String dailytransferId);

    List<TbDailytransferOrder> queryAllOrdersBySiteId(@Param("siteId") String siteId, @Param("dailytransferId") String dailytransferId);
}