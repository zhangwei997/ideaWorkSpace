package com.xakj.platform.platform.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
* Created by Mybatis Generator 2019/05/24
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
    private Date addDate;

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
	* 保险公司名称
	*/
    private String insuranceCompanyName;

    private static final long serialVersionUID = 1L;
}