package com.xakj.platform.filter;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;

@Component
@Aspect
@Log
public class ControllerResultAop {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("execution(public HttpResponse com.xakj..*.controller..*.*(..))")
    public HttpResponse serviceAop(ProceedingJoinPoint pjp) throws Throwable{
        HttpResponse httpResponse = new HttpResponse();

        //使用 HttpServletResponse 自定义处理的，不处理，返回空
        Boolean aBoolean =StringUtils.contains(pjp.getSignature().toString(),"HttpServletResponse");
        System.out.println(aBoolean);
        System.out.println(pjp.getSignature().toString());
        if(StringUtils.contains(pjp.getSignature().toString(),"HttpServletResponse")){
            pjp.proceed();
            return null;
        }

        //否则统一封装返回结果
        HttpResponse result = new HttpResponse();
        try {
            Date startDate = new Date();
            result = (HttpResponse) pjp.proceed();
            Date endDate = new Date();
           Object[] obs=pjp.getArgs();
           if(obs!=null&&obs.length>=1){

               logger.info(pjp.getSignature().toShortString()+"的入参为：[{}]",JSONArray.toJSONString(obs[0]));
               logger.warn(pjp.getSignature().toShortString()+"方法的执行时长为："+(endDate.getTime()-startDate.getTime())+"毫秒");
             /*  logger.info(pjp.getSignature().toShortString()+"的入参为：[{}]",JSONArray.toJSONString(pjp.getArgs()));
               logger.warn(pjp.getSignature().toShortString()+"方法的执行时长为："+(endDate.getTime()-startDate.getTime())+"毫秒");*/
           }
            result.setSuccess(true);
        } catch (AppException e) {
            logger.error("业务异常：",e);
            e.printStackTrace();
          //  String errorCode = e.getErrorCode();
            String message = e.getMessage();
           // result.setErrorCode("1111");
            result.setErrorMsg(message);
            result.setErrorMessage(message);
            result.setSuccess(false);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            logger.error("非法访问异常：",e);
            String message = e.getMessage();
            result.setErrorMsg("非法访问异常"+message);
            result.setErrorMessage("非法访问异常"+message);
            result.setSuccess(false);
        }
        catch (HttpRequestMethodNotSupportedException e) {
            e.printStackTrace();
            logger.error("错误的请求方式：",e);
            e.printStackTrace();
            result.setSuccess(false);

        }
        catch (SQLIntegrityConstraintViolationException e){
            e.printStackTrace();
            logger.error("SQL异常：",e);
            e.printStackTrace();
            String message = e.getMessage();
            result.setErrorMsg(message);
            result.setErrorMessage(message);
            result.setSuccess(false);
        }
        catch (Throwable e) {
            e.printStackTrace();
            logger.error(pjp.getSignature().toShortString()+"的入参为：[{}]",JSONArray.toJSONString(pjp.getArgs()));
            logger.error(pjp.getSignature().toShortString()+"系统内部异常：",e);
            result.setSuccess(false);
            result.setQuickErrorMsg(StringUtils.abbreviate(e.getMessage(), 300));
        }

        return result;
    }
}
