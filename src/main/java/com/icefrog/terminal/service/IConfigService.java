/*
 * Copyright 2019 版权所有
 *
 * @since 1.0
 * @author: icefrog.su@qq.com
 */

package com.icefrog.terminal.service;

import com.icefrog.terminal.util.ApiResult;

public interface IConfigService {
    
    String getValue(String key);
    
    ApiResult setValue(String key, String value);
    
}
