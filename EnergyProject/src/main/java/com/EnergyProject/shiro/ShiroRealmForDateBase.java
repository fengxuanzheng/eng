package com.EnergyProject.shiro;



import com.EnergyProject.pojo.EnergyUsername;

import com.EnergyProject.server.ENERGYUSERNAMEService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;

public class ShiroRealmForDateBase extends AuthorizingRealm {
    @Autowired
    private ENERGYUSERNAMEService energyusernameService;

    public static String usernameOriginPassword;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Object primaryPrincipal = principals.getPrimaryPrincipal();
         EnergyUsername shiro_usernam=(EnergyUsername)primaryPrincipal;
        HashSet<String> hashSet = new HashSet<>();
        String status = shiro_usernam.getStatus();
        if ("0".equals(status))
        {
            hashSet.add("管理员");
            hashSet.add("普通");
        }
        else if ("1".equals(status))
        {
            hashSet.add("普通");
        }
        else
        {
            hashSet.add("游客");
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(hashSet);

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken)token;
        String username = usernamePasswordToken.getUsername();
        Object credentials = usernamePasswordToken.getCredentials();
        String password=new String((char[])credentials);
        usernameOriginPassword=password;
        EnergyUsername energyUsername = energyusernameService.selectUsernameByUser(username);
        String usernamaPassword = energyUsername.getPassword();
        if(energyUsername==null)
        {
            throw  new AuthenticationException("登入失败");
        }
        String getsalt = energyUsername.getSalt();
        ByteSource bytes = ByteSource.Util.bytes(getsalt);
        energyUsername.setPassword("");
        energyUsername.setSalt("");
        energyUsername.setCreateTime(null);
        energyUsername.setUserId(null);
        energyUsername.setUpdateTime(null);
        energyUsername.setMobile(null);

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(energyUsername, usernamaPassword,bytes, getName());
        return simpleAuthenticationInfo;
    }
}
