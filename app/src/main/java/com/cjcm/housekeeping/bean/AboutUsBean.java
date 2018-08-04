package com.cjcm.housekeeping.bean;

import lombok.Data;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/26
 */
@Data
public class AboutUsBean {

    /**
     * success : true
     * errorCode : -1
     * msg : 操作成功
     * body : {"baseinfo":{"id":"1","updateBy":{"id":"1","loginFlag":"1","roleNames":"","admin":true},"updateDate":"2018-06-22 10:49:27","name":"伊宁捷洁家政","phone":"18591992168","adress":"开发区上海路万荣广场","email":"348040933@qq.com","qq":"348040933","banquan":"348040933"}}
     */

    private boolean success;
    private String errorCode;
    private String msg;
    private BodyBean body;

    @Data
    public static class BodyBean {
        /**
         * baseinfo : {"id":"1","updateBy":{"id":"1","loginFlag":"1","roleNames":"","admin":true},"updateDate":"2018-06-22 10:49:27","name":"伊宁捷洁家政","phone":"18591992168","adress":"开发区上海路万荣广场","email":"348040933@qq.com","qq":"348040933","banquan":"348040933"}
         */

        private BaseinfoBean baseinfo;

        @Data
        public static class BaseinfoBean {
            /**
             * id : 1
             * updateBy : {"id":"1","loginFlag":"1","roleNames":"","admin":true}
             * updateDate : 2018-06-22 10:49:27
             * name : 伊宁捷洁家政
             * phone : 18591992168
             * adress : 开发区上海路万荣广场
             * email : 348040933@qq.com
             * qq : 348040933
             * banquan : 348040933
             */

            private String id;
            private UpdateByBean updateBy;
            private String updateDate;
            private String name;
            private String phone;
            private String adress;
            private String email;
            private String qq;
            private String banquan;

            @Data
            public static class UpdateByBean {
                /**
                 * id : 1
                 * loginFlag : 1
                 * roleNames :
                 * admin : true
                 */

                private String id;
                private String loginFlag;
                private String roleNames;
                private boolean admin;
            }
        }
    }
}
