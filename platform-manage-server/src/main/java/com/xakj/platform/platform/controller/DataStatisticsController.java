package com.xakj.platform.platform.controller;

import com.xakj.platform.InsuranceSide.service.impl.UserService;
import com.xakj.platform.annotation.UserLoginToken;
import com.xakj.platform.filter.HttpResponse;
import com.xakj.platform.platform.model.vo.UserrecordReq;
import com.xakj.platform.platform.service.UserrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 数据统计报表
 * @Author: Hardy
 * @DateTime: 2019/5/20 10:37
 * @Verstion 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/DataStatistics")
public class DataStatisticsController {

    @Autowired
    UserrecordService userrecordService;

    @Autowired
    UserService userService;

    /**
     * 保险公司
     * @param userrecordReq
     * @return
     */
    @UserLoginToken
    @RequestMapping("/insuranceCompany")
    public HttpResponse insuranceCompany(@RequestBody UserrecordReq userrecordReq){
        return  new HttpResponse(userrecordService.selectInsuranceCompany(userrecordReq));
    }
    /**
     * 人员统计
     * @param userrecordReq
     * @return
     */
    @UserLoginToken
    @RequestMapping("/personnelStatistics")
    public HttpResponse personnelStatistics(@RequestBody UserrecordReq userrecordReq){
        return  new HttpResponse(userrecordService.selectPersonnelStatistics(userrecordReq));
    }

    /**
     * 全部统计
     * @param
     * @return
     */
    @UserLoginToken
    @RequestMapping("/selectAllStatistics")
    public HttpResponse selectAllStatistics(@RequestBody UserrecordReq userrecordReq){
        return  new HttpResponse(userService.selectAllStatistics(userrecordReq));
    }
}
