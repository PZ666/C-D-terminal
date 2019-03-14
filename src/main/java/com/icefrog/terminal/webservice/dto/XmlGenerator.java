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

/**
 * XmlGenerator with webservice
 *
 * @author icefrog.su@qq.com
 */
public class XmlGenerator {

	/***
	 * Generate webservice's xml string
	 * 
	 * @param serviceStation {@link ServiceStationDto}
	 * 
	 * @return xml with string
	 */
	public static String generateXmlWithWebService(ServiceStationDto serviceStation){
		StringBuffer xmlBuffer = new StringBuffer();
		xmlBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xmlBuffer.append("<serviceStation>");
		
			xmlBuffer.append("<userId>" + serviceStation.getUserId() + "</userId>");// 企业编码
			xmlBuffer.append("<rptDate>" + serviceStation.getRptDate() + "</rptDate>");// 报表日期
			
			for (ServiceStationReportDto serviceStationReport : serviceStation.getServiceStationReports()) {
				
				xmlBuffer.append("<serviceStationReport>");// 各站点交易信息
				xmlBuffer.append("<code>" + serviceStationReport.getCode() + "</code>");// 站点编码（ID)
				xmlBuffer.append("<name>" + serviceStationReport.getName() + "</name>");// 站点名称
				xmlBuffer.append("<countyType>" + serviceStationReport.getCountyType() + "</countyType>");// 站点类型:   1县级服务中心 2乡镇级服务站 3村级服务站
				xmlBuffer.append("<buyOrder>" + serviceStationReport.getBuyOrder() + "</buyOrder>");// 代买总订单数
				xmlBuffer.append("<saleOrder>" + serviceStationReport.getSaleOrder() + "</saleOrder>");// 代卖总订单数
				
				for (ServiceStationCommodityDto serviceStationCommodity : serviceStationReport.getServiceStationCommoditys()) {
					xmlBuffer.append("<serviceStationCommodity>");
					xmlBuffer.append("<commId>" + serviceStationCommodity.getCommId() + "</commId>");// 代买/代卖商品类别
					xmlBuffer.append("<money>" + serviceStationCommodity.getMoney() + "</money>");// 代买/代卖总金额
					xmlBuffer.append("</serviceStationCommodity>");
				}
				
				xmlBuffer.append("</serviceStationReport>");
			}
			
		xmlBuffer.append("</serviceStation>");
		return xmlBuffer.toString();
	}
}
