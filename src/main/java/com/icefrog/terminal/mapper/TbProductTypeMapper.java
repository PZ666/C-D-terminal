package com.icefrog.terminal.mapper;

import com.icefrog.terminal.model.TbProductType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbProductTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbProductType record);

    int insertSelective(TbProductType record);

    TbProductType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbProductType record);

    int updateByPrimaryKey(TbProductType record);
    
    int logicRemove(@Param("id") String id);
    
    List<TbProductType> pageQuery(@Param("broadType") String broadType, @Param("productType") String productType);
    
    List<TbProductType> queryAllProductTypes();
    
    TbProductType dimSearchProductType(@Param("typeName") String typeName);
    
    /**
     * 根据商品类型名称模糊查询
     *
     * @param productTypeName
     * @return
     */
    TbProductType queryProductInfoByName(@Param("productTypeName") String productTypeName);
}