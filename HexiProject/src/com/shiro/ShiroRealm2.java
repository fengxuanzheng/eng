package com.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class ShiroRealm2 extends AuthorizingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("第二个______调用这个方法");
        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        System.out.println("从数据库查询数据");
        if("unknown".equals(username))
        {
            throw new UnknownAccountException("用户不存在");
        }
        Object forusernamme=username;
        Object password="ce2f6417c7e1d32c1d81";
        String relme=getName();
       //计算盐值,盐值要唯一,可以用随机字符串或用户名
        //测试使用用户名为admin,其他用户名无法登入
        ByteSource bytes = ByteSource.Util.bytes(username);
        //源码底层统一使用SimpleHash(hashAlgorithmName, credentials, salt, hashIterations)来加密,其中salt是盐值
          /* 不使用盐值加密
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(forusernamme,password,relme);*/

        //第二种使用复杂构造器带上盐值加密,使就算原始密码一样加密后的值也不一样
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo("ShiroRealm2",password,bytes,relme);


        return simpleAuthenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("第二个________-______运行授权方法");
        return null;
    }
}
