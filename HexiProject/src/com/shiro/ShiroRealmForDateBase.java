package com.shiro;

import com.dao.Shiro_usernameMapper;
import com.pojo.Shiro_username;
import com.pojo.Shiro_usernameExample;
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
    private Shiro_usernameMapper shiro_usernameMapper;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Object primaryPrincipal = principals.getPrimaryPrincipal();
         Shiro_username shiro_usernam=(Shiro_username)primaryPrincipal;
        HashSet<String> hashSet = new HashSet<>();
        String status = shiro_usernam.getStatus();
        if ("0".equals(status))
        {
            hashSet.add("管理员");
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
        Shiro_usernameExample shiro_usernameExample = new Shiro_usernameExample();
        shiro_usernameExample.createCriteria().andUsernameEqualTo(username);
        List<Shiro_username> shiro_usernames = shiro_usernameMapper.selectByExampleWithBLOBs(shiro_usernameExample);
        if(shiro_usernames==null || shiro_usernames.isEmpty())
        {
            throw  new AuthenticationException("登入失败");
        }
        String getsalt = shiro_usernameMapper.getsalt(username);
        ByteSource bytes = ByteSource.Util.bytes(getsalt);

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(shiro_usernames.get(0), shiro_usernames.get(0).getPassword(),bytes, getName());
        return simpleAuthenticationInfo;
    }
}
