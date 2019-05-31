package com.xakj.platform.filter;

/**
 * @Description: TODO
 * @Author: Hardy
 * @DateTime: 2019/5/15 15:20
 * @Verstion 1.0
 */
public enum ResultEnum {
    /**
     * 返回状态
     */
    INVALID_SINGTIMEOUT("108010", "超时"),
    INVALID_PERMISSION_DENIED("108002", "无效许可，权限不足"),
    OK("108003", "正常"),
    ;

    /**
     * 业务码
     */
    private String code;
    /**
     * 描述
     */
    private String describe;

    ResultEnum(String code, String describe) {
        this.code = code;
        this.describe = describe;
    }
}
