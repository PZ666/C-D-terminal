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
