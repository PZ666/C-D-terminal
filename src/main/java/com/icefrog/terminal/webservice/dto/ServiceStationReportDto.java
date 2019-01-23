package com.icefrog.terminal.webservice.dto;

import java.util.ArrayList;
import java.util.List;

/***
 * WebService xml - > 各站点交易信息
 * 
 * @author icefrog.su@qq.com
 */
public class ServiceStationReportDto {
	
	// 站点编码(ID)
	private String code;
	
	// 站点名称
	private String name;
	
	// 站点类型:   1县级服务中心 2乡镇级服务站 3村级服务站
	private String countyType;
	
	// 代买总订单数
	private String buyOrder;
	
	// 代卖总订单数
	private String saleOrder;

	// 商品信息列表
	private List<ServiceStationCommodityDto> serviceStationCommoditys = new ArrayList<>();

	@Override
	public String toString() {
		return "ServiceStationReportDto [code=" + code + ", name=" + name + ", countyType=" + countyType + ", buyOrder="
				+ buyOrder + ", saleOrder=" + saleOrder + "]";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountyType() {
		return countyType;
	}

	public void setCountyType(String countyType) {
		this.countyType = countyType;
	}

	public String getBuyOrder() {
		return buyOrder;
	}

	public void setBuyOrder(String buyOrder) {
		this.buyOrder = buyOrder;
	}

	public String getSaleOrder() {
		return saleOrder;
	}

	public void setSaleOrder(String saleOrder) {
		this.saleOrder = saleOrder;
	}

	public List<ServiceStationCommodityDto> getServiceStationCommoditys() {
		return serviceStationCommoditys;
	}

	public void setServiceStationCommoditys(List<ServiceStationCommodityDto> serviceStationCommoditys) {
		this.serviceStationCommoditys = serviceStationCommoditys;
	}
}
