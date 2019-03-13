/*
 * Copyright 2019 版权所有
 *
 * @since 1.0
 * @author: icefrog.su@qq.com
 */

package com.icefrog.terminal.event;

import com.icefrog.terminal.mapper.TbUserMapper;
import com.icefrog.terminal.model.TbUser;
import com.icefrog.terminal.util.Constans;
import com.icefrog.terminal.util.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SpringBootBeforeEvent implements ApplicationRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(SpringBootBeforeEvent.class);
    
    @Autowired
    private TbUserMapper userMapper;
    
    public static final String DEFAULT_ADMIN_USERNAME = "admin";
    
    public static final String DEFAULT_ADMIN_PASSWORD = "123456";
    
    public static final String DEFAULT_ADMIN_MOBILE = DEFAULT_ADMIN_USERNAME;
    
    /**
     * Callback used to run the bean.
     *
     * @param args incoming application arguments
     * @throws Exception on error
     */
    @Override
    public void run(ApplicationArguments args) {
        // 初始化内置用户
        logger.info("initialize user resource...");
        TbUser user = userMapper.selectByPrimaryKey(Constans.DEFAULT_ADMIN_ID);
        if(user == null){
            user = buildUserEntity();
            userMapper.insertSelective(user);
        }else if(user.getIsDel() == 1){
            user.setIsDel(0);
            userMapper.updateByPrimaryKeySelective(user);
        }
    }
    
    private TbUser buildUserEntity(){
        TbUser user = new TbUser();
        user.setId(Constans.DEFAULT_ADMIN_ID);
        user.setUserName(DEFAULT_ADMIN_USERNAME);
        user.setMobile(DEFAULT_ADMIN_MOBILE);
        
        String salt = String.valueOf(System.currentTimeMillis());
        user.setSalt(salt);
        user.setPassword(MD5Utils.MD5Encoder(DEFAULT_ADMIN_PASSWORD, salt));
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setEmail(null);
        user.setFaceUrl("/image/default-face.png");
        user.setIsDel(0);
        return user;
    }
}
