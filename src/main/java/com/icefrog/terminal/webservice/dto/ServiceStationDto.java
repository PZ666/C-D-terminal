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

import java.util.ArrayList;
import java.util.List;

/***
 * WebService xml - > 文档根
 * 
 * @author icefrog.su@qq.com
 *
 */
public class ServiceStationDto {
	
	// 企业编码
	private String userId;
	
	// 报表日期 yyyy-MM-dd
	private String rptDate;

	// 各站点交易信息列表
	private List<ServiceStationReportDto> serviceStationReports = new ArrayList<>();

	@Override
	public String toString() {
		return "ServiceStationDto [userId=" + userId + ", rptDate=" + rptDate + "]";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRptDate() {
		return rptDate;
	}

	public void setRptDate(String rptDate) {
		this.rptDate = rptDate;
	}

	public List<ServiceStationReportDto> getServiceStationReports() {
		return serviceStationReports;
	}

	public void setServiceStationReports(List<ServiceStationReportDto> serviceStationReports) {
		this.serviceStationReports = serviceStationReports;
	}
}
