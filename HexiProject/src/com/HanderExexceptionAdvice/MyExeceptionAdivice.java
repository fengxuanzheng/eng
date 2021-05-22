package com.HanderExexceptionAdvice;

import com.utils.RequestInformation;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Map;

@ControllerAdvice
public class MyExeceptionAdivice {

    @ExceptionHandler(value = {UnauthorizedException.class})
    @ResponseBody
    public RequestInformation unauthorizedException()
    {
        RequestInformation requestInformation = new RequestInformation();
        Map<String, Object> stringObjectMap = requestInformation.getStringObjectMap();
        stringObjectMap.put("message","您没有权限");
        return requestInformation;
    }

    @ExceptionHandler(value = {SQLException.class})
    @ResponseBody
    public RequestInformation sQLExceptionException()
    {
        RequestInformation requestInformation = new RequestInformation();
        Map<String, Object> stringObjectMap = requestInformation.getStringObjectMap();
        stringObjectMap.put("message","数据库处理错误");
        return requestInformation;
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public RequestInformation exceptionException(Exception exception)
    {
        RequestInformation requestInformation = new RequestInformation();
        Map<String, Object> stringObjectMap = requestInformation.getStringObjectMap();
        stringObjectMap.put("message",exception.getMessage());
        return requestInformation;
    }
}
