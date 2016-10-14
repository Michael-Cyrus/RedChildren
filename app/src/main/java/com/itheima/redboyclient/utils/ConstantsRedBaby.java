package com.itheima.redboyclient.utils;

//时间戳：是unix表示时间的一种方式通常都是一串比较长的数字，可以根据需求进行转换
public class ConstantsRedBaby {
    public static final String URL_SERVER = "http://192.168.56.1:8080/RedBabyServer/";
    public static final String URL_TOPIC = URL_SERVER + "topic.json";
    public static final String URL_HOME = URL_SERVER + "home";
    public static final String URL_LOGIN = URL_SERVER + "login";


    /**
     * topic请求码
     */
    public static final int REQUEST_CODE_TOPIC = 0;
    /**
     * home请求码
     */
    public static final int REQUEST_CODE_HOME = 1;
}
