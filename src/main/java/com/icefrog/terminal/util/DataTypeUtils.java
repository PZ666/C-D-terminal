/*
 * Copyright 2019 版权所有
 *
 * @since 1.0
 * @author: icefrog.su@qq.com
 */

package com.icefrog.terminal.util;

import org.apache.commons.lang.StringUtils;

public class DataTypeUtils {
    
    public static boolean isInteger(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
    
    public static Integer getInt(String intStr, int defaultVal){
        if(StringUtils.isEmpty(intStr)){
            return defaultVal;
        }
        if(isInteger(intStr)){
            return Integer.parseInt(intStr);
        }
        return defaultVal;
    }
    
}
