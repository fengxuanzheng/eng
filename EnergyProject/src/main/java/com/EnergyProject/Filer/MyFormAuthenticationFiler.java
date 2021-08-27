package com.EnergyProject.Filer;

import com.EnergyProject.pojo.EnergyUsername;
import com.EnergyProject.server.ENERGYUSERNAMEService;
import com.EnergyProject.shiro.ShiroRealmForDateBase;
import com.EnergyProject.utils.RequestInformation;
import com.google.gson.Gson;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

public class MyFormAuthenticationFiler extends FormAuthenticationFilter {

    @Autowired
    private ENERGYUSERNAMEService energyusernameService;

    public static Boolean isRememberMeError=false;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {



        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest=(HttpServletRequest) request;
            boolean options = httpServletRequest.getMethod().toUpperCase().equals("OPTIONS");
            if (options) {
                return true;
            }
        }
        Subject subject = getSubject(request, response);
        if (subject.isRemembered())
        {
            Object salt=null;
            String username = ((EnergyUsername) subject.getPrincipal()).getUsername();
            if ("admin".equals(username))
            {
                salt= ByteSource.Util.bytes("UOG");
            }
            else
            {
                salt=ByteSource.Util.bytes(username+"UOG");
            }
            SimpleHash simpleHash = new SimpleHash("MD5", ShiroRealmForDateBase.usernameOriginPassword, salt, 100);
            EnergyUsername energyUsername = energyusernameService.selectUsernameByUser(((EnergyUsername)subject.getPrincipal()).getUsername());
            if (energyUsername.getPassword().equals(simpleHash.toString()))
            {
                return (subject.isAuthenticated() || subject.isRemembered()) && subject.getPrincipal() != null;
            }
            else
            {
                isRememberMeError=true;
            }
        }


           return super.isAccessAllowed(request, response, mappedValue);

    }




    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse response1 = (HttpServletResponse) response;
        response1.setHeader("Content-Type","application/json;charset=UTF-8");
        response1.setHeader("Access-Control-Allow-Credentials","true");
        response1.setHeader("Access-Control-Allow-Origin","http://10.228.1.230");
        //response1.setHeader("Access-Control-Allow-Origin","http://localhost:8080");
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
