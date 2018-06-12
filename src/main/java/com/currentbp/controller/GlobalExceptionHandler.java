package com.currentbp.controller;

import com.alibaba.fastjson.JSON;
import com.currentbp.entry.BusinessException;
import com.currentbp.entry.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

/**
 * @author baopan
 * @createTime 20180607
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResultData handleBusinessException(HttpServletRequest request, BusinessException e) {
        logger.warn("handleBusinessException: " + e.getMessage(), e);
        return ResultData.failed(e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResultData handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("handleHttpMessageNotReadableException : " + e.getMessage(), e);
        return ResultData.failed("参数解析失败");
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public ResultData handleNullPointerException(HttpServletRequest request, NullPointerException e) {
        logger.error("handleNullPointerException : requestParams:" + JSON.toJSONString(request.getParameterMap()), e);
        return ResultData.failed("操作异常(空)");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResultData handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException e) {
        logger.error("handleIllegalArgumentException : requestParams:" + JSON.toJSONString(request.getParameterMap()), e);
        return ResultData.failed(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultData handleException(HttpServletRequest request, Exception e) {
//        logger.info("===>"+JSON.toJSONString(request));
        logger.error("handleException : requestParams:" + JSON.toJSONString(request.getParameterNames()), e);
        return ResultData.failed(e.getMessage());
    }
}
