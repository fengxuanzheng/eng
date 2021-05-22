package com.service;

import com.dao.Shiro_usernameMapper;
import com.pojo.Shiro_username;
import com.pojo.Shiro_usernameBase64;
import com.pojo.Shiro_usernameExample;
import com.utils.Md5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShiroUsernameService {
    @Autowired
    private Shiro_usernameMapper shiro_usernameMapper;

    private final static Map<Integer,byte[]> newphoto=new HashMap<>();



    public String registUser(Shiro_username shiroUsername)
    {
        String password = shiroUsername.getPassword();
        Md5Hash newpassword = new Md5Hash(password, Md5Util.salt(shiroUsername.getUsername()),100);
        shiroUsername.setPassword(newpassword.toString());
        shiroUsername.setSalt(Md5Util.salt(shiroUsername.getUsername()));
        int i = shiro_usernameMapper.insertSelective(shiroUsername);
        if (i!=0)
        {
            return "success";
        }
        return null;
    }


    public String insertphoto(byte[] bytes)
    {
        Subject currentUser = SecurityUtils.getSubject();
        Shiro_username principal =(Shiro_username) currentUser.getPrincipal();
        newphoto.put(principal.getUserid(),bytes);
        Integer userid = principal.getUserid();
        Shiro_username shiro_username = new Shiro_username();
        shiro_username.setUserid(userid);
        shiro_username.setUserPhoto(bytes);
        int i = shiro_usernameMapper.updateByPrimaryKeySelective(shiro_username);
        if (i!=0)
        {
            return "success";

        }
        return null;
    }

    public String getPhotoBase64()
    {
        Subject currentUser = SecurityUtils.getSubject();
        Shiro_username principal =(Shiro_username) currentUser.getPrincipal();
        boolean equals = Arrays.equals(principal.getUserPhoto(), newphoto.get(principal.getUserid()));
        if (principal.getUserPhoto()==null || !equals)
        {
            Shiro_username shiro_username = shiro_usernameMapper.selectByPrimaryKey(principal.getUserid());
            principal.setUserPhoto(shiro_username.getUserPhoto());
            Base64.Encoder encoder = Base64.getEncoder();
            String s = encoder.encodeToString(shiro_username.getUserPhoto());
            return "data:image/png;base64,"+s;

        }
        Base64.Encoder encoder = Base64.getEncoder();
        String s = encoder.encodeToString(principal.getUserPhoto());
        return "data:image/png;base64,"+s;
    }

    public Map<String,String> sessionStatus()
    {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        Subject currentUser = SecurityUtils.getSubject();
        Shiro_username principal = (Shiro_username)currentUser.getPrincipal();
        stringStringHashMap.put("user",principal.getUsername());
        stringStringHashMap.put("status",principal.getStatus());
        stringStringHashMap.put("station",principal.getStation());
        return stringStringHashMap;
    }

    public List<Shiro_usernameBase64> getuser()
    {
        List<Shiro_username> shiro_usernames = shiro_usernameMapper.selectByExampleWithBLOBs(null);
        ArrayList<Shiro_usernameBase64> shiro_usernameBase64s = new ArrayList<>();

        for (Shiro_username shiro_username:shiro_usernames)
        {
            Shiro_usernameBase64 shiro_usernameBase64 = new Shiro_usernameBase64();
            shiro_usernameBase64.setCreatetime(shiro_username.getCreatetime());
            shiro_usernameBase64.setEmail(shiro_username.getEmail());
            shiro_usernameBase64.setUpdatetime(shiro_username.getUpdatetime());
            shiro_usernameBase64.setMobile(shiro_username.getMobile());
            shiro_usernameBase64.setSalt(shiro_username.getSalt());
            shiro_usernameBase64.setUserid(shiro_username.getUserid());
            shiro_usernameBase64.setPassword(shiro_username.getPassword());
            shiro_usernameBase64.setUsername(shiro_username.getUsername());
            shiro_usernameBase64.setStatus(shiro_username.getStatus());
            shiro_usernameBase64.setStation(shiro_username.getStation());
            if (shiro_username.getUserPhoto()!=null)
            {
                Base64.Encoder encoder = Base64.getEncoder();
                String s = encoder.encodeToString(shiro_username.getUserPhoto());
                shiro_usernameBase64.setUserPhoto("data:image/png;base64,"+s);
            }
            shiro_usernameBase64s.add(shiro_usernameBase64);
        }
        return shiro_usernameBase64s;
    }

    public String updataStatus(Integer integer,String status,String station)
    {

        Shiro_username shiro_usernameNEW = new Shiro_username();
        Shiro_username shiro_username = shiro_usernameMapper.selectByPrimaryKey(integer);

        shiro_usernameNEW.setUserid(integer);
        shiro_usernameNEW.setStatus(status);
        shiro_usernameNEW.setStation(station);
            int i = shiro_usernameMapper.updateByPrimaryKeySelective(shiro_usernameNEW);


        if (i!=0)
        {
            return "success";
        }
        return null;
    }

    public String deleteuser(Integer integer)
    {
        int i = shiro_usernameMapper.deleteByPrimaryKey(integer);
        newphoto.remove(integer);
        if (i != 0) {
            return "success";
        }
        return null;
    }

    public boolean isusername(String username)
    {
        Shiro_usernameExample shiro_usernameExample = new Shiro_usernameExample();
        Shiro_usernameExample.Criteria criteria = shiro_usernameExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<Shiro_username> shiro_usernames = shiro_usernameMapper.selectByExampleWithBLOBs(shiro_usernameExample);
        if (shiro_usernames == null || shiro_usernames.isEmpty()) {
            return false;
        }
        else
        {
            return true;
        }
    }



}
