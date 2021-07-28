package com.EnergyProject.controller;


import com.EnergyProject.pojo.EnergyUsername;
import com.EnergyProject.server.ENERGYUSERNAMEService;
import com.EnergyProject.utils.Md5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 魔法少女
 * @since 2021-07-27
 */
@RestController
@RequestMapping("/energyUsername")
public class EnergyUsernameController {

    @Autowired
    private ENERGYUSERNAMEService ENERGYUSERNAMEService;


    @PostMapping("/loginEnergy")
    public Boolean loginEnergyMananger(@RequestBody Map<String, String> stringObjectMap, HttpServletResponse httpServletResponse) throws IOException {
        Subject currentUser = SecurityUtils.getSubject();

        try {
            if (!currentUser.isAuthenticated()) {
                UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(stringObjectMap.get("username"), stringObjectMap.get("password"));
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

        return true;
    }

    @PostMapping("/rejectEnergyUser")
    public Boolean rejectEnergyUser(@RequestBody(required = false) EnergyUsername energyUsername)
    {
        String password = energyUsername.getPassword();
        String salt = Md5Util.salt(energyUsername.getUsername());
        energyUsername.setSalt(salt);
        SimpleHash simpleHash = new SimpleHash("MD5", password, salt, 100);
        energyUsername.setPassword(simpleHash.toString());
        Integer integer = ENERGYUSERNAMEService.rejectEnergyUsername(energyUsername);
        return integer!=null;
    }

    @GetMapping("/getLoginUser")
    public EnergyUsername getLoginUser()
    {
        Subject subject = SecurityUtils.getSubject();

        return (EnergyUsername) subject.getPrincipal();
    }

}

