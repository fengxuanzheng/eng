package com.shiro;

import java.util.LinkedHashMap;

public class Shiro_linkHashmap {

   /* public LinkedHashMap<String,String> gethashmao()
    {
        LinkedHashMap<String, String> stringStringLinkedHashMap = new LinkedHashMap<>();
        return stringStringLinkedHashMap;
    }*/

    public static LinkedHashMap<String,String> getlinghashmap()
    {
        LinkedHashMap<String, String> stringStringLinkedHashMap = new LinkedHashMap<>();

        /*/pages/login.jsp = anon
            /shiro_login = anon
            /pages/date.jsp = authc
            /logout= logout

            /pages/user.jsp = roles[user]
            /pages/admin.jsp = roles[admin]*/
        stringStringLinkedHashMap.put("/pages/login.jsp","anon");
        stringStringLinkedHashMap.put("/shiro_login","anon");
        stringStringLinkedHashMap.put("/testshiro","anon");
        stringStringLinkedHashMap.put(" /pages/date.jsp","authc");
        stringStringLinkedHashMap.put("/pages/user.jsp","roles[user]");
        stringStringLinkedHashMap.put("/pages/admin.jsp"," roles[admin]");
        stringStringLinkedHashMap.put("/logout","logout");
        stringStringLinkedHashMap.put("/**","authc");

        return stringStringLinkedHashMap;
    }
}
