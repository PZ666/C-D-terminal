/*
 * Copyright 2019 版权所有
 *
 * @since 1.0
 * @author: icefrog.su@qq.com
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
