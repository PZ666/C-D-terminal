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

package com.icefrog.terminal.service.impl;

import com.icefrog.terminal.mapper.TbConfigMapper;
import com.icefrog.terminal.model.TbConfig;
import com.icefrog.terminal.service.IConfigService;
import com.icefrog.terminal.util.ApiResult;
import com.icefrog.terminal.util.BaseService;
import com.icefrog.terminal.util.IDGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ConfigServiceImpl extends BaseService implements IConfigService {
    
    @Autowired
    private TbConfigMapper configMapper;
    
    @Override
    public String getValue(String key) {
        TbConfig config = configMapper.queryConfigByKey(key);
        if(config == null){
            return null;
        }
        return config.getConfigValue();
    }
    
    @Override
    public ApiResult setValue(String key, String value) {
        TbConfig config = configMapper.queryConfigByKey(key);
        if(config == null){
            config = buildConfig(key, value);
            configMapper.insertSelective(config);
        }else{
            config.setConfigValue(value);
            configMapper.updateByPrimaryKeySelective(config);
        }
        return success("");
    }
    
    private TbConfig buildConfig(String key, String value){
        TbConfig config = new TbConfig();
        config.setId(IDGenerate.buildID());
        config.setConfigKey(key);
        config.setConfigValue(value);
        config.setCreateTime(new Date());
        config.setUpdateTime(new Date());
        return config;
    }
}
