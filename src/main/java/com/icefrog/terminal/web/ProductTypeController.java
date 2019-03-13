package com.icefrog.terminal.web;

import com.icefrog.terminal.model.TbProductType;
import com.icefrog.terminal.model.TbUser;
import com.icefrog.terminal.service.IProductTypeService;
import com.icefrog.terminal.util.ApiBaseController;
import com.icefrog.terminal.util.ApiResult;
import com.icefrog.terminal.util.Constans;
import com.icefrog.terminal.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/***
 * 商品类型controller
 * 
 * @author icefrog.su@qq.com
 *
 */
@Controller
@RequestMapping("/product-type")
public class ProductTypeController extends ApiBaseController {

	@Autowired
	private IProductTypeService productTypeService;
	
	@RequestMapping("/toAddProductType")
	public String toAddProductType(){
		return "product-type/product-type-add";
	}
	
	@RequestMapping("/toProductTypes")
	public String toProductTypes(){
		return "product-type/product-type-list";
	}
	
	@RequestMapping("/toViewProductType")
	public String toViewProductType(@RequestParam(value="id",required=false) String id,
			HttpServletRequest request){
		TbProductType productType = productTypeService.selectByPrimaryKey(id);
		request.setAttribute("productType", productType);
		return "product-type/product-type-view";
	}
	
	@RequestMapping("/toEditProductType")
	public String toEditProductType(@RequestParam(value="id",required=false) String id,
			HttpServletRequest request){
		TbProductType productType = productTypeService.selectByPrimaryKey(id);
		request.setAttribute("productType", productType);
		return "product-type/product-type-edit";
	}
	
	@RequestMapping("/initProductTypes")
	@ResponseBody
	public ApiResult initProductTypes(@RequestParam(name="broadType",required=false) String broadType,
									  @RequestParam(name="productType",required=false) String productType,
									  @RequestParam(name="pageIndex",required=false) String pageIndex,
									  @RequestParam(name="pageSize",required=false) String pageSize){
		return productTypeService.pageQuery(broadType, productType, pageIndex, pageSize, "/product-type/initProductTypes");
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public ApiResult save(@RequestParam(name="broadType",required=false) String broadType,
						  @RequestParam(name="productType",required=false) String productType,
						  @RequestParam(name="generateBuyNo",required=false) String generateBuyNo,
						  @RequestParam(name="generateSellNo",required=false) String generateSellNo,
						  HttpServletRequest request){
		TbUser user = (TbUser) SessionUtil.getAttribute(request, Constans.LOGINED_SESSION_USER);
		return productTypeService.save(broadType, productType, generateBuyNo, generateSellNo, user.getId(), "/product-type/save");
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public ApiResult update(@RequestParam(name="id",required=false) String id,
						    @RequestParam(name="broadType",required=false) String broadType,
						    @RequestParam(name="productType",required=false) String productType,
						    @RequestParam(name="generateBuyNo",required=false) String generateBuyNo,
						    @RequestParam(name="generateSellNo",required=false) String generateSellNo,
						  HttpServletRequest request){
		TbUser user = (TbUser)SessionUtil.getAttribute(request, Constans.LOGINED_SESSION_USER);
		return productTypeService.update(id,broadType, productType, generateBuyNo, generateSellNo, user.getId(), "/product-type/save");
	}
	
	@RequestMapping("/batchRemove")
	@ResponseBody
	public ApiResult batchRemove(@RequestParam(name="ids",required=false) String ids){
		return productTypeService.batchRemove(ids, "/product-type/batchRemove");
	}
}
