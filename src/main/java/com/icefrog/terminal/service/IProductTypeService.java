package com.icefrog.terminal.service;

import com.icefrog.terminal.model.TbProductType;
import com.icefrog.terminal.util.ApiResult;

import java.util.List;

/***
 * 商品类型service
 * 
 * @author ICE FROG
 *
 */
public interface IProductTypeService {

	TbProductType selectByPrimaryKey(String id);
	
	int updateByPrimaryKeySelective(TbProductType productType);
	
	int insertSelective(TbProductType productType);
	
	ApiResult save(String broadType, String productType, String generateBuyNo, String generateSellNo, String userId, String fn);
	
	ApiResult update(String id, String broadType, String productType, String generateBuyNo, String generateSellNo, String userId, String fn);
	
	ApiResult pageQuery(String broadType, String productType, String pageIndex, String pageSize, String fn);
	
	ApiResult batchRemove(String ids, String fn);
	
	List<TbProductType> queryAllProductTypes();
	
	TbProductType dimSearchProductType(String typeName);
}
