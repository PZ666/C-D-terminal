package com.icefrog.terminal.webservice.dto;

/***
 * WebService xml - > 商品信息节点
 * 
 * @author icefrog.su@qq.com
 *
 */
public class ServiceStationCommodityDto {

	// 代买/代卖商品类别
	private String commId;
	
	// 代买/代卖总金额
	private String money;

	@Override
	public String toString() {
		return "ServiceStationCommodityDto [commId=" + commId + ", money=" + money + "]";
	}

	public String getCommId() {
		return commId;
	}

	public void setCommId(String commId) {
		this.commId = commId;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
}
