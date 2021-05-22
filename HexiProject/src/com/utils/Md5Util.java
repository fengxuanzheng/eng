package com.utils;

public class Md5Util {

    private final static String  publicsal="UOG";
    public static String salt(String username)
    {
        String newsalt=username+publicsal;
        return newsalt;

    }
}
