package com.shiro;

import com.dao.Shiro_usernameMapper;
import com.pojo.Shiro_username;
import com.pojo.Shiro_usernameExample;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.utils.Md5Util;

import java.util.List;

@Controller
public class ShiroContoller {
    @Autowired
    private Shiro_usernameMapper shiro_usernameMapper;

    @RequestMapping("/shiro_login")
    public String shiro_login(String username,String password)
    {
        Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.isAuthenticated())
        {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (AuthenticationException e) {
                System.out.println("登入失败" + e.getMessage());
            }


        }
        return "redirect:/pages/shiroforok.jsp";

    }

    @PostMapping("/regist")
    public String regist(String username,String password,String mobile,String email)
    {/*
        String newpassword = new Md5Hash(password, Md5Util.salt(username, password), 100).toString();
        Shiro_username shiro_username = new Shiro_username();
        shiro_username.setUsername(username);
        shiro_username.setPassword(newpassword);
        shiro_username.setEmail(email);
        shiro_username.setMobile(Long.parseLong(mobile));
        shiro_username.setSalt(Md5Util.salt(username,password));
        shiro_username.setStatus("1");
        Shiro_usernameExample shiro_usernameExample = new Shiro_usernameExample();
        shiro_usernameExample.createCriteria().andUsernameEqualTo(username);
        List<Shiro_username> shiro_usernames = shiro_usernameMapper.selectByExample(shiro_usernameExample);
        if(shiro_usernames==null || shiro_usernames.isEmpty())
        {
            int insert = shiro_usernameMapper.insertSelective(shiro_username);


        }*/
        return "redirect:/pages/login.jsp";

    }

    @RequestMapping("/tests")
    public String ajax(Model model)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ctp","欧尼酱");
      model.addAttribute("ajax","欧尼酱");
        return "../index";
    }

    @RequestMapping("/logout")
    public String logout()
    {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "forward:/WEB-INF/jsp/login.jsp";
    }
}
