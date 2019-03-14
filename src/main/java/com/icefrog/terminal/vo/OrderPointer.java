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

package com.icefrog.terminal.vo;

import com.icefrog.terminal.util.DataTypeUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OrderPointer {

    private String orderId;
    
    private String sumMoney;
    
    private Integer buyCount;
    
    private Integer sellCount;
    
    public static OrderPointer pointer(String orderId, JSONArray jsonArray){
        for(int i = 0; i<jsonArray.size(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if(jsonObject.getString("orderId").equals(orderId)){
                OrderPointer pointer = new OrderPointer();
                pointer.setOrderId(orderId);
                pointer.setSumMoney(jsonObject.getString("sumMoney"));
                pointer.setBuyCount(DataTypeUtils.getInt(jsonObject.getString("buyCount"), 0));
                pointer.setSellCount(DataTypeUtils.getInt(jsonObject.getString("sellCount"), 0));
                return pointer;
            }
        }
        return null;
    }
    
    public String getOrderId() {
        return orderId;
    }
    
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    public String getSumMoney() {
        return sumMoney;
    }
    
    public void setSumMoney(String sumMoney) {
        this.sumMoney = sumMoney;
    }
    
    public Integer getBuyCount() {
        return buyCount;
    }
    
    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }
    
    public Integer getSellCount() {
        return sellCount;
    }
    
    public void setSellCount(Integer sellCount) {
        this.sellCount = sellCount;
    }
}
