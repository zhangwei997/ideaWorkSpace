package com.xakj.platform.platform.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.xakj.platform.platform.mapper.UserTwoMapper;
import com.xakj.platform.platform.model.User;

@Service
public class UserService{

    @Resource
    private UserTwoMapper userMapperTwo;

    public int deleteByPrimaryKey(String id){
        return userMapperTwo.deleteByPrimaryKey(id);
    }

    public int insert(User record){
        return userMapperTwo.insert(record);
    }

    public int insertSelective(User record){
        return userMapperTwo.insertSelective(record);
    }

    public User selectByPrimaryKey(String id){
        return userMapperTwo.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(User record){
        return userMapperTwo.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(User record){
        return userMapperTwo.updateByPrimaryKey(record);
    }

    public User findUserById(String userId) {
        return userMapperTwo.selectByPrimaryKey(userId);
    }
    public User findByUsername(User user){
        return userMapperTwo.selectByPrimaryKey(user.getUserName());
    }
}
