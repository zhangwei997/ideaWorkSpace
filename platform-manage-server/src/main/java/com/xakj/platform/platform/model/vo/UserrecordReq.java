package com.xakj.platform.platform.model.vo;

import lombok.Data;

/**
 * @Description: TODO
 * @Author: Hardy
 * @DateTime: 2019/5/20 10:44
 * @Verstion 1.0
 */
@Data
public class UserrecordReq {

    private String patientName;

    private String staffName;

    private String iphone;

    /**
     * 就诊记录表id/主键
     */
    private  String acographyId;

    private  String  medicalBillingNo;

    private  String curPage;

    private  String pageSize;

    private  String  beginTime;

    private  String  endTime;

    private  String  insuranceCompanyId;
    /**
     * 保险公司名称
     */
    private  String insuranceCompanyName;

    private  String userId;

    private String name;
}
