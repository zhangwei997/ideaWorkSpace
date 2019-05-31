package com.xakj.platform.InsuranceSide.service.impl;



import com.xakj.platform.InsuranceSide.mapper.UserMapper;
import com.xakj.platform.InsuranceSide.model.User;
import com.xakj.platform.platform.model.vo.UserrecordReq;
import com.xakj.platform.platform.service.UserrecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Hardy Generator
 * @date 2019-05-014 20:52
 */
@Service("UserService")
public class UserService {

    @Resource
    UserrecordService userrecordService;
    @Resource
    UserMapper userMapper;
    public User findByUsername(User user){
        return userMapper.selectByPrimaryKey(user.getUserName());
    }
    public User findUserById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }



    public List<User> findUserAll(UserrecordReq userrecordReq) {
        return userMapper.selectByUserName(userrecordReq);
    }

    public List<Map<String,Object>> selectAllStatistics(UserrecordReq userrecordReq) {
        List<Map<String,Object>> list =new ArrayList<>();
        list= userMapper.selectAllStatistics(userrecordReq);
        list.forEach(stringObjectMap -> {
            userrecordReq.setInsuranceCompanyId(stringObjectMap.get("CompanyId").toString());
            stringObjectMap.put("tasks",userrecordService.selectCountAll(userrecordReq));
            stringObjectMap.put("processingTime",userrecordService.selectPocessingTimeAll(userrecordReq));
        });
        return list;
    }



}
