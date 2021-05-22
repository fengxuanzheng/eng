package com.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;

public class ShiroRealm extends AuthorizingRealm {//既要登入验证又要授权只需要继承AuthorizingRealm就可以,如果只要验证就
    //继承Authentication就行

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("第一个________调用这个方法");
        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        System.out.println("从数据库查询数据");
        if("unknown".equals(username))
        {
            throw new UnknownAccountException("用户不存在");
        }
        Object forusernamme=username;
        Object password=null;
        if("admin".equals(username))
        {
            password="038bdaf98f2037b31f1e75b5b4c9b26e";

        }
        if("user".equals(username))
        {
            password="098d2c478e9c11555ce2823231e02ec1";

        }
        String relme=getName();
       //计算盐值,盐值要唯一,可以用随机字符串或用户名
        //测试使用用户名为admin,其他用户名无法登入
        ByteSource bytes = ByteSource.Util.bytes(username);
        //源码底层统一使用SimpleHash(hashAlgorithmName, credentials, salt, hashIterations)来加密,其中salt是盐值
          /* 不使用盐值加密
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(forusernamme,password,relme);*/

        //第二种使用复杂构造器带上盐值加密,使就算原始密码一样加密后的值也不一样
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(forusernamme,password,bytes,relme);


        return simpleAuthenticationInfo;
    }

    @Override
    //测方法是实现授权的
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("第一个_______运行权限授权方法");
        //获取当前用户信息
        Object primaryPrincipal = principals.getPrimaryPrincipal();
        //利用登入用户信息来授予当前角色和权限
        HashSet<String> objects = new HashSet<>();
        objects.add("user");
        if("admin".equals(primaryPrincipal))
        {
            objects.add("admin");
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(objects);


        return simpleAuthorizationInfo;
    }
}
