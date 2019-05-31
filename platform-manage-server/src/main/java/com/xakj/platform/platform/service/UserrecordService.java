package com.xakj.platform.platform.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xakj.platform.InsuranceSide.model.User;
import com.xakj.platform.InsuranceSide.service.impl.UserService;
import com.xakj.platform.platform.model.vo.UserrecordReq;
import com.xakj.platform.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;
import com.xakj.platform.platform.model.Userrecord;
import com.xakj.platform.platform.mapper.UserrecordMapper;

@Service
@Slf4j
public class UserrecordService{

    @Resource
    private UserrecordMapper userrecordMapper;

    @Autowired
    private UserService userService;

    public int deleteByPrimaryKey(String userRecordId){
        return userrecordMapper.deleteByPrimaryKey(userRecordId);
    }

    public int insert(Userrecord record){
        return userrecordMapper.insert(record);
    }

    public int insertSelective(Userrecord record){
        return userrecordMapper.insertSelective(record);
    }

    public Userrecord selectByPrimaryKey(String userRecordId){
        return userrecordMapper.selectByPrimaryKey(userRecordId);
    }

    public int updateByPrimaryKeySelective(Userrecord record){
        return userrecordMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Userrecord record){
        return userrecordMapper.updateByPrimaryKey(record);
    }


    public PageInfo<Userrecord> selectInsuranceCompany(UserrecordReq userrecordReq){
        PageHelper.startPage(Integer.parseInt(userrecordReq.getCurPage()), Integer.parseInt(userrecordReq.getPageSize()));
        List<Userrecord> list =userrecordMapper.selectInsuranceCompany(userrecordReq);
        list.forEach(userrecord -> {
            try {
                userrecord.setIsData(userrecord.getIsData().equals("1")?"数据":"影像");
            } catch (Exception e) {
                log.error("数据转换异常");
                e.printStackTrace();
            }
        });
        //用PageInfo对结果进行包装
        PageInfo<Userrecord> page = new PageInfo<Userrecord>(list);
        return page;
    }

     public PageInfo<User> selectPersonnelStatistics(UserrecordReq userrecordReq){
        PageHelper.startPage(Integer.parseInt(userrecordReq.getCurPage()), Integer.parseInt(userrecordReq.getPageSize()));
         List<User> users=userService.findUserAll(userrecordReq);

        users.forEach(user -> {
            //获取单均处理时间
            user.setProcessingTime(userrecordMapper.selectPocessingTime(user.getId()));
            //获取累计处理条数
            user.setProcessingCount(userrecordMapper.selectCount(user.getId()));
            user.setRole("单证收集");
        });
        //用PageInfo对结果进行包装
        PageInfo<User> page = new PageInfo<User>(users);
        return page;
    }


    public String selectCountAll(UserrecordReq userrecordReq){
       return userrecordMapper.selectCountAll(userrecordReq);
    }

    public Long selectPocessingTimeAll(UserrecordReq userrecordReq){
        return  userrecordMapper.selectPocessingTimeAll(userrecordReq);
    }

}
