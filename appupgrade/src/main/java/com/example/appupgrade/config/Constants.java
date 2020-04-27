package com.example.appupgrade.config;

/**
 * 常量类
 */
public final class Constants {

    public static class App {
        public static final String API_DOMAIN_DEBUG = "http://120.79.59.8:8080/AppManage";
        public static final String API_DOMAIN_RELEASE = "http://120.79.59.8:8080/AppManage";
       // public static final String API_DOMAIN_DEBUG = "http://89u9d9.natappfree.cc/SpringIot";
        //public static final String API_DOMAIN_RELEASE = "http://89u9d9.natappfree.cc/SpringIot";
        public static final int UPGRADE_DELAY_TIME = 1000; //在首页延迟1s启动检测升级
    }
}
