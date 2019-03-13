/*
 * Copyright 2019 版权所有
 *
 * @since 1.0
 * @author: icefrog.su@qq.com
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
