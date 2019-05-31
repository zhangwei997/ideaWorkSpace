package com.xakj.platform.platform.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
* Created by Hardy Generator 2019/05/20
*/
@Data
public class Userrecord implements Serializable {
    /**
	*  id
	*/
    private String userRecordId;

    /**
	* 用户id
	*/
    private String userId;

    /**
	* 用户名
	*/
    private String userName;

    /**
	* 真实姓名
	*/
    private String actualName;

    /**
	* 患者记录申请时间
	*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applyDate;    /**

    /**
	* 记录新增时间
	*/
    private Date addDate;

    /**
	* 用户申请记录id
	*/
    private String applyRecordId;

    /**
	* 保险公司id
	*/
    private String insuranceCompanyId;

    /**
	* 医院id
	*/
    private String hospitalId;

    /**
	* 医院名称
	*/
    private String hospitalName;

    /**
	* 数据类型 1单证2数据
	*/
    private String isData;

    /**
	* 处理时长
	*/
    private String processingTime;

    /**
	* 操作人角色
	*/
    private String role;

    /**
	* 保险公司名称
	*/
    private String insuranceCompanyName;

    /**
	* 患者id
	*/
    private String patientId;

    /**
	* 患者名称
	*/
    private String patientName;

    /**
     * 客户电话
     */
    private String customerPhone;


    private static final long serialVersionUID = 1L;
}