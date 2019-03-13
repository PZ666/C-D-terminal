package com.icefrog.terminal.mapper;

import com.icefrog.terminal.model.TbUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TbUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbUser record);

    int insertSelective(TbUser record);

    TbUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbUser record);

    int updateByPrimaryKey(TbUser record);
    
    TbUser queryUserByMobile(@Param("mobile") String mobile);
}