package com.xakj.platform.platform.mapper;

import com.xakj.platform.platform.model.Userrecord;
import com.xakj.platform.platform.model.vo.UserrecordReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* Created by Hardy Generator 2019/05/20
*/
@Mapper
public interface UserrecordMapper {
    int deleteByPrimaryKey(String userRecordId);

    int insert(Userrecord record);

    int insertSelective(Userrecord record);

    Userrecord selectByPrimaryKey(String userRecordId);

    int updateByPrimaryKeySelective(Userrecord record);

    int updateByPrimaryKey(Userrecord record);

    List<Userrecord> selectInsuranceCompany(UserrecordReq userrecordReq);


    String selectCount(String userId);

    Long selectPocessingTime(String userId);

    String selectCountAll(UserrecordReq userrecordReq);

    Long selectPocessingTimeAll(UserrecordReq userrecordReq);
}