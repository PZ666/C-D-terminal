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

package com.icefrog.terminal.webservice;

import java.rmi.RemoteException;

import com.cvicse.www.service.SyncServiceStationOperationRequest;
import com.cvicse.www.service.SyncServiceStationOperationResponse;
import com.cvicse.www.service.SyncServiceStationPortType;
import com.cvicse.www.service.SyncServiceStationPortTypeProxy;
import com.icefrog.terminal.webservice.dto.XmlGenerator;
import com.icefrog.terminal.webservice.dto.ServiceStationDto;

/***
 * WebService util
 *
 * @since 1.0
 * @author icefrog.su@qq.com
 */
public class PushUtils {
	
	/***
	 * Webservice调用工具 将ServiceStationDto封装好的实际业务数据进行xml转换并且使用WebService传输到商务部接口
	 * @param serviceStation 实际封装的特务数据DTO
	 * @return SyncServiceStationOperationResponse.${instance}.getOut(). 该方法返回值无实际意义
	 * @throws RemoteException RemoteException
	 */
	public static String push(ServiceStationDto serviceStation) throws RemoteException{
		SyncServiceStationPortTypeProxy proxy = new SyncServiceStationPortTypeProxy();
		proxy.setEndpoint(WebServiceConstance.WSDL);
	
		SyncServiceStationPortType portType = proxy.getSyncServiceStationPortType();
		
		String in = XmlGenerator.generateXmlWithWebService(serviceStation);
		
		SyncServiceStationOperationResponse response = portType.syncServiceStationOperation(new SyncServiceStationOperationRequest(in));
	
		return response.getOut();
	}
}
