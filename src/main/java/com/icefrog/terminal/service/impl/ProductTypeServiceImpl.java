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

package com.icefrog.terminal.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icefrog.terminal.mapper.TbProductTypeMapper;
import com.icefrog.terminal.model.TbProductType;
import com.icefrog.terminal.service.IProductTypeService;
import com.icefrog.terminal.util.ApiResult;
import com.icefrog.terminal.util.BaseService;
import com.icefrog.terminal.util.IDGenerate;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ProductTypeServiceImpl extends BaseService implements IProductTypeService {

	private static final Logger logger = LoggerFactory.getLogger(ProductTypeServiceImpl.class);
	
	@Autowired
	private TbProductTypeMapper productTypeMapper;
	
	@Override
	public TbProductType selectByPrimaryKey(String id) {
		return productTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TbProductType productType) {
		return productTypeMapper.updateByPrimaryKeySelective(productType);
	}

	@Override
	public int insertSelective(TbProductType productType) {
		return productTypeMapper.insertSelective(productType);
	}

	@Override
	public ApiResult save(String broadType, String productType, String generateBuyNo, String generateSellNo,
						  String userId, String fn) {
		TbProductType p = new TbProductType();
		p.setId(IDGenerate.buildID());
		p.setCreateId(userId);
		p.setUpdateId(userId);
		p.setCreateTime(new Date());
		p.setUpdateTime(new Date());
		p.setBroadType(broadType);
		p.setProductType(productType);
		p.setGenerateBuyNo(generateBuyNo);
		p.setGenerateSellNo(generateSellNo);
		p.setIsdel(0);
		int insertResult = this.insertSelective(p);
		return insertResult > 0 ? success(fn) : error(fn);
	}

	@Override
	public ApiResult update(String id, String broadType, String productType, String generateBuyNo,
			String generateSellNo, String userId, String fn) {
		TbProductType p = new TbProductType();
		p.setId(id);
		p.setUpdateId(userId);
		p.setUpdateTime(new Date());
		p.setBroadType(broadType);
		p.setProductType(productType);
		p.setGenerateBuyNo(generateBuyNo);
		p.setGenerateSellNo(generateSellNo);
		int updateResult = this.updateByPrimaryKeySelective(p);
		return updateResult > 0 ? success(fn) : error(fn);
	}

	@Override
	public ApiResult pageQuery(String broadType, String productType, String pageIndex, String pageSize, String fn) {
		
		if(StringUtils.isEmpty(broadType) || "-1".equals(broadType)){
			broadType = null;
		}else{
			broadType = "%" + broadType + "%";
		}
		if(StringUtils.isEmpty(productType)){
			productType = null;
		}else{
			productType = "%" + productType + "%";
		}
		
		PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		List<TbProductType> productTypes = productTypeMapper.pageQuery(broadType, productType);
		PageInfo<TbProductType> info = new PageInfo<>(productTypes);
		
		return success(fn, ApiResult.SUCCESS_MESSAGE, "productTypes", info);
	}

	@Transactional
	@Override
	public ApiResult batchRemove(String ids, String fn) {
		if(StringUtils.isEmpty(ids)){
			return success(fn,"未删除任何数据!");
		}
		String[] idArray = ids.split(",");
		int sumCount = 0;
		for (String id : idArray) {
			int count = productTypeMapper.logicRemove(id);
			if(count < 1){
				String message = "删除失败,指定id删除结果<1  id:"+id;
				logger.error(message);
				throw new RuntimeException(message);
			}
			sumCount += count;
		}
		return success(fn, "删除成功,本次共删除"+sumCount+"条数据!");
	}

	@Override
	public List<TbProductType> queryAllProductTypes() {
		return productTypeMapper.queryAllProductTypes();
	}

	@Override
	public TbProductType dimSearchProductType(String typeName) {
		return productTypeMapper.dimSearchProductType("%" + typeName + "%");
	}

}
