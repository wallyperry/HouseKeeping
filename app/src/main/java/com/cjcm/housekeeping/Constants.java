package com.cjcm.housekeeping;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/20
 */
public interface Constants {

    /**
     * 服务器地址
     */
    interface Api {
        String BASE_URL = "http://121.42.242.202";
    }

    interface TypeId {
        /**
         * 捷洁特色
         */
        String Jjts = "root2";
        String Jjts_Info = "捷洁特色";
        /**
         * 家政服务
         */
        String Jzfw = "root1";
        String Jzfw_Info = "家政服务";
    }

    /**
     * 网络超时时间
     */
    interface NetTime {
        /**
         * 连接超时
         */
        int connect = 15;
        /**
         * 读超时
         */
        int read = 15;
        /**
         * 写超时
         */
        int write = 12;
    }

    /**
     * 约定的异常代码
     */
    interface ErrorCode {

        /**
         * 未知错误
         */
        int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        int PARSE_ERROR = 1001;
        /**
         * 网络错误
         */
        int NETWORK_ERROR = 1002;
        /**
         * 协议出错
         */
        int HTTP_ERROR = 1003;
        /**
         * 连接超时
         */
        int TIME_OUT = 1004;
        /**
         * 证书出错
         */
        int SSL_ERROR = 1005;
    }

    interface Key {
        /**
         * SharedPreferences加解密密码
         */
        String SP_PASS = "199532";
    }
}
