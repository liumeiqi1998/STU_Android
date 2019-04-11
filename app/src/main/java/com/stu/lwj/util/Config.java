package com.stu.lwj.util;

/**
 * Created by Roger on 2018/10/29.
 */
public class Config {
    /**private static final String[] CATEGORY = {
            "手机数码", "手机配件", "电脑平板", "电脑外设",
            "宿舍用品", "电器", "衣服服饰", "箱包配饰", "美妆个护",
            "交通工具", "运动户外", "玩具", "乐器", "书籍", "其他"
    };*/

    private static String WEB_URL = "http://192.168.137.1:8080/Servlet/";

    //本机地址
    private final static String LOCALHOST_URL = "http://192.168.137.1:8080/Servlet/";

    //腾讯服务器地址
    private final static String TENCENT_SERVER_URL = "http://134.175.6.143:8080/Servlet/";


    public static String getWebUrl() {
        return WEB_URL;
    }

    public static void useLocalhost(){
        WEB_URL = LOCALHOST_URL;
    }

    public static void useTencent(){
        WEB_URL = TENCENT_SERVER_URL;
    }
}
