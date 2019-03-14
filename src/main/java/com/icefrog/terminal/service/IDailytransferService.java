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

package com.icefrog.terminal.service;

import com.icefrog.terminal.model.TbDailytransfer;
import com.icefrog.terminal.model.TbDailytransferOrder;
import com.icefrog.terminal.util.ApiResult;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.List;

public interface IDailytransferService {
    
    ApiResult save(String jsonStr, String userId, String fn) throws ParseException;
    
    ApiResult update(String dailytransferId, String jsonStr, String userId, String fn) throws ParseException;
    
    ApiResult pageQuery(String pageIndex, String pageSize, String status, String startTime, String endTime, String fn);
    
    ApiResult batchRemove(String ids, String fn);
    
    TbDailytransfer selectByPrimaryKey(String id);
    
    List<TbDailytransferOrder> queryAllByDailytransferId(String dailytransferId);
    
    /**
     * 通过webservice上传数据到商务部
     * @param id dailytransfer id
     * @param fn function id
     * @return ApiResult
     */
    ApiResult Upstream(String id, String fn) throws RemoteException;
}
