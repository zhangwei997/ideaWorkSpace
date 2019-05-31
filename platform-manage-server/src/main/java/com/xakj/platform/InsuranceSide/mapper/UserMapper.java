package com.xakj.platform.InsuranceSide.mapper;


import com.xakj.platform.InsuranceSide.model.User;
import com.xakj.platform.platform.model.vo.UserrecordReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
* Created by Hardy Generator 2019/05/20
*/
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    List<User> selectByUserName(UserrecordReq userrecordReq);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<Map<String,Object>> selectAllStatistics(UserrecordReq userrecordReq);
}