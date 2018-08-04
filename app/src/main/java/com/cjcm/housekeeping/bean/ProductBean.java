package com.cjcm.housekeeping.bean;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/26
 */
@Data
public class ProductBean implements Serializable {
    private boolean success;
    private String errorCode;
    private String msg;
    private BodyBean body;

    @Data
    public static class BodyBean implements Serializable {
        private PageBean page;

        @Data
        public static class PageBean implements Serializable {
            private int pageNo;
            private int pageSize;
            private int count;
            private String html;
            private int firstResult;
            private int maxResults;
            private List<ListBean> list;

            @Data
            public static class ListBean implements Serializable {
                private String id;
                private String remarks;
                private CreateByBean createBy;
                private String createDate;
                private UpdateByBean updateBy;
                private String updateDate;
                private String name;
                private String title;
                private String price;
                private String wei;
                private String content;
                private String type;
                private String image;

                @Data
                public static class CreateByBean implements Serializable {
                    private String id;
                    private String loginFlag;
                    private boolean admin;
                    private String roleNames;
                }

                @Data
                public static class UpdateByBean implements Serializable {
                    private String id;
                    private String loginFlag;
                    private boolean admin;
                    private String roleNames;
                }
            }
        }
    }
}
