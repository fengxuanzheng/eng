package com.shiro;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Service;

@Service
public class Realsm {
    @RequiresRoles({"admin"})
    public void tests()
    {
        System.out.println("执行权限方法测试");
    }
}
