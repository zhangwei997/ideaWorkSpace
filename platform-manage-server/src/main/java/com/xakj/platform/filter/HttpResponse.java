package com.xakj.platform.filter;

import lombok.Data;

@Data
public class HttpResponse {
    private boolean success;
    private String errorCode;
    private String errorMsg;
    private String errorMessage;
    private Object data;
    private String quickErrorMsg; //用于前端查看简略异常信息

    public HttpResponse() {
        this.success = true;
    }
    public HttpResponse(Object data) {
        this.data = data;
        this.success = true;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        if (null == errorCode || "null".equals(errorCode)) {
            return "";
        } else {
            return errorCode;
        }
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        if (null == errorMsg || "null".equals(errorMsg)) {
            return "";
        } else {
            return errorMsg;
        }
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        if (null == data) {
            data = "";
        }
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    public String getQuickErrorMsg() {
        return quickErrorMsg;
    }
    public void setQuickErrorMsg(String quickErrorMsg) {
        this.quickErrorMsg = quickErrorMsg;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
