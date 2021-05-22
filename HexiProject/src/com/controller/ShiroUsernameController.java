package com.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.pojo.Shiro_username;
import com.pojo.Shiro_usernameBase64;
import com.service.ShiroUsernameService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShiroUsernameController {
    @Autowired
    private ShiroUsernameService shiroUsernameService;

    @Autowired
    private DefaultKaptcha captchaProducer;


    @RequestMapping("/registerUser")
    @ResponseBody
    public String registerUser(@RequestBody Shiro_username shiro_username)
    {
        String s = shiroUsernameService.registUser(shiro_username);
        return s;
    }

    @RequestMapping("/loginuser")
    @ResponseBody
    public String loginuser(String username, String password,String kapVaild, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setHeader("Content-Type","application/json;charset=UTF-8");
        Subject currentUser = SecurityUtils.getSubject();
        String attribute = (String) currentUser.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (attribute.equalsIgnoreCase(kapVaild)) {


            try {
                if (!currentUser.isAuthenticated()) {
                    UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
                    usernamePasswordToken.setRememberMe(true);
                    currentUser.login(usernamePasswordToken);
                }

            } catch (AuthenticationException e) {
                PrintWriter writer = httpServletResponse.getWriter();
                writer.write("登入失败,请检查认证信息");
                writer.flush();
                writer.close();
                return null;
            }

        }
        else
        {
            return "验证码错误";
        }
        return "success";
    }

    @RequestMapping("/insertuserPhoto")
    @ResponseBody
    public String insertuserphoto( @RequestParam(value = "photoData",required = false) MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty()) {
            byte[] bytes = multipartFile.getBytes();
            String insertphoto = shiroUsernameService.insertphoto(bytes);
            return "success";
        }
        return null;
    }

    @RequestMapping("/getphotobase64")
    @ResponseBody
    public String getphotobase64()
    {
        String photoBase64 = shiroUsernameService.getPhotoBase64();
        return photoBase64;
    }

    @RequestMapping("/getusrStatus")
    @ResponseBody
    public Map<String,String> getuserStatus()
    {
        return shiroUsernameService.sessionStatus();

    }

    @RequestMapping("/getuser")
    @ResponseBody
    public Map<String,Object> getuser(Integer page,Integer sizes)
    {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        Page<Object> objects = PageHelper.startPage(page, sizes);
        List<Shiro_usernameBase64> getuser = shiroUsernameService.getuser();
        long total = objects.getTotal();
        stringObjectHashMap.put("datas",getuser);
        stringObjectHashMap.put("total",total);
        return stringObjectHashMap;
    }

    @RequestMapping("/updataStatus")
    @ResponseBody
    @RequiresRoles("管理员")
    public String updataStatus(Integer id,String status,String station)
    {
        String s = shiroUsernameService.updataStatus(id,status,station);
        return s;
    }

    @RequestMapping("/deleteuser")
    @ResponseBody
    @RequiresRoles("管理员")
    public String deleteUser(Integer id)
    {
        String deleteuser = shiroUsernameService.deleteuser(id);
        return deleteuser;
    }

    @RequestMapping("/isusername")
    @ResponseBody
    public boolean isusername(String username)
    {

        return shiroUsernameService.isusername(username);

    }

    @RequestMapping("/kaptcha")
    public void getkaptch(HttpServletRequest request, HttpServletResponse response)
    {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        // 生成验证码
        String capText = captchaProducer.createText();
        Subject subject = SecurityUtils.getSubject();
        HttpSession session = request.getSession();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY,capText);

        // 向客户端写出
        ServletOutputStream out = null;
        try {
            BufferedImage bi = captchaProducer.createImage(capText);
            out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
