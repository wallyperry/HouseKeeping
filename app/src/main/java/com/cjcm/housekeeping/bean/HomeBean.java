package com.cjcm.housekeeping.bean;

import java.util.List;

import lombok.Data;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/26
 */
@Data
public class HomeBean {


    /**
     * success : true
     * errorCode : -1
     * msg : 操作成功
     * body : {"data":[{"id":"c165b665c6d84dc99f2c411e2d8a908f","remarks":"banner_center_1","createBy":{"id":"1","loginFlag":"1","admin":true,"roleNames":""},"createDate":"2018-06-26 11:55:21","updateBy":{"id":"1","loginFlag":"1","admin":true,"roleNames":""},"updateDate":"2018-06-27 18:53:04","titel":"用洁净诠释你的家","type":"2","content":"|/housek/userfiles/1/files/carouse/carouse/2018/06/%E5%AE%A3%E4%BC%A0%E5%9B%BE1.jpg|/housek/userfiles/1/files/carouse/carouse/2018/06/%E5%AE%B6%E6%94%BF%E5%AE%A3%E4%BC%A0%E5%9B%BE8.jpg|/housek/userfiles/1/files/carouse/carouse/2018/06/%E5%AE%A3%E4%BC%A0%E5%9B%BE3.jpg|/housek/userfiles/1/files/carouse/carouse/2018/06/%E5%AE%B6%E6%94%BF%E5%AE%A3%E4%BC%A0%E5%9B%BE7.jpg|/housek/userfiles/1/files/carouse/carouse/2018/06/%E5%AE%B6%E6%94%BF%E5%AE%A3%E4%BC%A0%E5%9B%BE7.jpg","image":"/housek/userfiles/1/files/carouse/carouse/2018/06/%E5%AE%B6%E6%94%BF%E5%AE%A3%E4%BC%A0%E5%9B%BE7.jpg"},{"id":"618fba74e4324049a462807126a75c1b","remarks":"banner_top_3","createBy":{"id":"1","loginFlag":"1","admin":true,"roleNames":""},"createDate":"2018-06-26 11:55:01","updateBy":{"id":"1","loginFlag":"1","admin":true,"roleNames":""},"updateDate":"2018-06-26 15:37:02","titel":"家政服务值得你的选择","type":"1","content":"","image":"/housek/userfiles/1/files/carouse/carouse/2018/06/%E5%AE%B6%E6%94%BF3.jpg"},{"id":"a33a24935c504bfe8fad109586b8585e","remarks":"banner_top_2","createBy":{"id":"1","loginFlag":"1","admin":true,"roleNames":""},"createDate":"2018-06-26 11:54:39","updateBy":{"id":"1","loginFlag":"1","admin":true,"roleNames":""},"updateDate":"2018-06-26 15:36:33","titel":"家政我们是认真的","type":"1","content":"","image":"/housek/userfiles/1/files/carouse/carouse/2018/06/%E5%AE%B6%E6%94%BF%E5%AE%A3%E4%BC%A0%E5%9B%BE6.jpg"},{"id":"914d765a4ebd45a68baf786f808972b3","remarks":"banner_top_1","createBy":{"id":"1","loginFlag":"1","admin":true,"roleNames":""},"createDate":"2018-06-26 11:54:16","updateBy":{"id":"1","loginFlag":"1","admin":true,"roleNames":""},"updateDate":"2018-06-26 15:36:12","titel":"家政服务找我们","type":"1","content":"","image":"/housek/userfiles/1/files/carouse/carouse/2018/06/%E5%AE%B6%E6%94%BF1.jpg"},{"id":"36b9ee93cc7f454bb777eef4ea6447a3","remarks":"banner_bottom_1","createBy":{"id":"1","loginFlag":"1","admin":true,"roleNames":""},"createDate":"2018-06-26 11:55:39","updateBy":{"id":"1","loginFlag":"1","admin":true,"roleNames":""},"updateDate":"2018-06-26 11:59:08","titel":"捷洁家政为您服务","type":"3","content":"","image":"/housek/userfiles/1/files/carouse/carouse/2018/06/%E5%AE%B6%E6%94%BF2.jpg"}]}
     */

    private boolean success;
    private String errorCode;
    private String msg;
    private BodyBean body;

    @Data
    public static class BodyBean {
        private List<DataBean> data;

        @Data
        public static class DataBean {
            /**
             * id : c165b665c6d84dc99f2c411e2d8a908f
             * remarks : banner_center_1
             * createBy : {"id":"1","loginFlag":"1","admin":true,"roleNames":""}
             * createDate : 2018-06-26 11:55:21
             * updateBy : {"id":"1","loginFlag":"1","admin":true,"roleNames":""}
             * updateDate : 2018-06-27 18:53:04
             * titel : 用洁净诠释你的家
             * type : 2
             * content : |/housek/userfiles/1/files/carouse/carouse/2018/06/%E5%AE%A3%E4%BC%A0%E5%9B%BE1.jpg|/housek/userfiles/1/files/carouse/carouse/2018/06/%E5%AE%B6%E6%94%BF%E5%AE%A3%E4%BC%A0%E5%9B%BE8.jpg|/housek/userfiles/1/files/carouse/carouse/2018/06/%E5%AE%A3%E4%BC%A0%E5%9B%BE3.jpg|/housek/userfiles/1/files/carouse/carouse/2018/06/%E5%AE%B6%E6%94%BF%E5%AE%A3%E4%BC%A0%E5%9B%BE7.jpg|/housek/userfiles/1/files/carouse/carouse/2018/06/%E5%AE%B6%E6%94%BF%E5%AE%A3%E4%BC%A0%E5%9B%BE7.jpg
             * image : /housek/userfiles/1/files/carouse/carouse/2018/06/%E5%AE%B6%E6%94%BF%E5%AE%A3%E4%BC%A0%E5%9B%BE7.jpg
             */

            private String id;
            private String remarks;
            private CreateByBean createBy;
            private String createDate;
            private UpdateByBean updateBy;
            private String updateDate;
            private String titel;
            private String type;
            private String content;
            private String image;

            @Data
            public static class CreateByBean {
                /**
                 * id : 1
                 * loginFlag : 1
                 * admin : true
                 * roleNames :
                 */

                private String id;
                private String loginFlag;
                private boolean admin;
                private String roleNames;
            }

            @Data
            public static class UpdateByBean {
                /**
                 * id : 1
                 * loginFlag : 1
                 * admin : true
                 * roleNames :
                 */

                private String id;
                private String loginFlag;
                private boolean admin;
                private String roleNames;
            }
        }
    }
}
