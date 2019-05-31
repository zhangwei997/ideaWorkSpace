package com.xakj.platform.platform.mapper;

import com.xakj.platform.platform.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
* Created by Mybatis Generator 2019/05/24
*/
@Mapper
public interface UserTwoMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}