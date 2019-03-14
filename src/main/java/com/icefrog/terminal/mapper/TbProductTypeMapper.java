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