package com.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*@Controller
public class TestForShiro {
    @Autowired
    private ShiroRealmForDateBase shiroRealmForDateBase;



    public void test()
    {
        DefaultSecurityManager defaultWebSecurityManager = new DefaultSecurityManager();
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        defaultWebSecurityManager.setRealm(iniRealm);
        //自定义realm

        SecurityUtils.setSecurityManager(defaultWebSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        System.out.println("是否已经登入：" + subject.isAuthenticated());
        if(!subject.isAuthenticated())
        {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("uog", "123456");
            subject.login(usernamePasswordToken);
        }
        System.out.println("是否已经登入：" + subject.isAuthenticated());
        System.out.println("是否有角色" + subject.hasRole("admin"));
        System.out.println("检查权限授权状态" + subject.isPermitted("product:create"));
        System.out.println("用户名" + subject.getPrincipal());


    }


    @RequestMapping("/testshiro")
    public String testshiro(String username,String password)
    {
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated())
        {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            usernamePasswordToken.setRememberMe(true);
            try {
                subject.login(usernamePasswordToken);
            } catch (AuthenticationException e) {
                System.out.println("登入失败" + e.getMessage()); //INFO, stdout
            }
        }
        return "redirect:/pages/shiroforok.jsp";

    }
}*/
