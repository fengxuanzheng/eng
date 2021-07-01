package com.EnergyProject.Filer;

import com.EnergyProject.utils.RequestInformation;
import com.google.gson.Gson;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

public class MyFormAuthenticationFiler extends FormAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {



        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest=(HttpServletRequest) request;
            boolean options = httpServletRequest.getMethod().toUpperCase().equals("OPTIONS");
            if (options) {
                return true;
            }
        }


           return super.isAccessAllowed(request, response, mappedValue);

    }


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse response1 = (HttpServletResponse) response;
        response1.setHeader("Content-Type","application/json;charset=UTF-8");
        response1.setHeader("Access-Control-Allow-Credentials","true");
        response1.setHeader("Access-Control-Allow-Origin","http://localhost");
        RequestInformation requestInformation = new RequestInformation();
        Map<String, Object> stringObjectMap = requestInformation.getStringObjectMap();
        stringObjectMap.put("message","用户未登入");
        Gson gson = new Gson();
        String s = gson.toJson(stringObjectMap);
        PrintWriter writer = response1.getWriter();
        writer.write(s);
        writer.flush();
        writer.close();
        return false;

    }
}
