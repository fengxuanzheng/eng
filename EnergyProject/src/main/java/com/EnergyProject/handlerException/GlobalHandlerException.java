package com.EnergyProject.handlerException;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public Map<String, Object> UnauthorizedExceptionHandler(Exception exception, HttpServletResponse httpServletResponse)
    {
        httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");
        httpServletResponse.setHeader("Access-Control-Allow-Origin","http://localhost:8080");
        httpServletResponse.setHeader("Content-Type","application/json;charset=UTF-8");
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("message",exception.getMessage());
        return stringObjectHashMap;
    }

}
