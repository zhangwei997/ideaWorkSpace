package com.xakj.platform.InsuranceSide.model;

import lombok.Data;

import java.io.Serializable;

/**
* Created by Hardy Generator 2019/05/20
*/
@Data
public class User implements Serializable {
    /**
	* 用户表id
	*/
    private String id;

    /**
	* 用户名
	*/
    private String userName;

    /**
	* 密码
	*/
    private String passWord;

    /**
	* 电话
	*/
    private String phone;

    /**
	* 记录新增时间
	*/
    private String addDate;

    /**
	* 账号所属公司id
	*/
    private String companyId;

    /**
	* 状态
	*/
    private String status;

    /**
	* 真实姓名
	*/
    private String actualName;

    /**
	* 角色
	*/
    private String role;

    /**
     * 处理任务数量
     */
    private String processingCount;

    /**
     * 处理时长
     */
    private Long processingTime;

    private String insuranceCompanyName;


    private static final long serialVersionUID = 1L;
}