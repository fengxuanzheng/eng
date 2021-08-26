package com.EnergyProject.Filer;

import com.EnergyProject.utils.RequestInformation;
import com.google.gson.Gson;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;

@Component("myLogoutFiler")
public class MyLogoutFiler extends LogoutFilter {
    private static final Logger log = LoggerFactory.getLogger(MyLogoutFiler.class);
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

        Subject subject = getSubject(request, response);
        HttpServletResponse httpServletResponse=(HttpServletResponse)response;
        httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");
        //httpServletResponse.setHeader("Access-Control-Allow-Origin","http://10.228.1.230");
        httpServletResponse.setHeader("Access-Control-Allow-Origin","http://localhost:8080");
        httpServletResponse.setHeader("Content-Type","application/json;charset=UTF-8");
        // Check if POST only logout is enabled
        if (isPostOnlyLogout()) {

            // check if the current request's method is a POST, if not redirect
            if (!WebUtils.toHttp(request).getMethod().toUpperCase(Locale.ENGLISH).equals("POST")) {
                return onLogoutRequestNotAPost(request, response);
            }
        }
        //try/catch added for SHIRO-298:
        try {
            subject.logout();
            Map<String, Object> stringObjectMap = new RequestInformation().getStringObjectMap();
            stringObjectMap.put("message","成功登出");
            String logoutmessage = new Gson().toJson(stringObjectMap);
            httpServletResponse.getWriter().write(logoutmessage);
        } catch (SessionException ise) {
            log.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
        }
        return false;

    }
}
