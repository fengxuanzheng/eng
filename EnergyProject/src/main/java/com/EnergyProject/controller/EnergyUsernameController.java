package com.EnergyProject.controller;


import com.EnergyProject.server.ENERGYUSERNAMEService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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

}

