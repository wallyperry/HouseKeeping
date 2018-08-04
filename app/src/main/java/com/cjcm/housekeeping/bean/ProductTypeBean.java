package com.cjcm.housekeeping.bean;

import java.util.List;

import lombok.Data;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/7/23
 */
@Data
public class ProductTypeBean {

    /**
     * success : true
     * errorCode : -1
     * msg : 操作成功
     * body : {"data":[{"id":"cf907d6b374c4f7da721367963768f06","remarks":"上门维修","createBy":{"id":"1","loginFlag":"1","admin":true,"roleNames":""},"createDate":"2018-07-19 13:11:34","updateBy":{"id":"1","loginFlag":"1","admin":true,"roleNames":""},"updateDate":"2018-07-19 13:18:02","parentIds":"0,root1,","name":"上门维修","sort":120,"hasChildren":false,"parentId":"root1"},{"id":"1","remarks":"定制服务","createBy":{"id":"1","loginFlag":"1","admin":true,"roleNames":""},"createDate":"2018-07-19 13:28:33","updateBy":{"id":"1","loginFlag":"1","admin":true,"roleNames":""},"updateDate":"2018-07-23 09:34:06","parentIds":"0,root1,","name":"定制服务","sort":30,"hasChildren":false,"parentId":"root1"},{"id":"3cbe6c9582204f9d8a1f57e67181b646","remarks":"家庭保洁","createBy":{"id":"1","loginFlag":"1","admin":true,"roleNames":""},"createDate":"2018-07-19 13:10:27","updateBy":{"id":"1","loginFlag":"1","admin":true,"roleNames":""},"updateDate":"2018-07-19 13:10:27","parentIds":"0,root1,","name":"家庭保洁","sort":60,"hasChildren":false,"parentId":"root1"},{"id":"431d708da3e84372aa68d4ace6ea3fda","remarks":"家电清洗","createBy":{"id":"1","loginFlag":"1","admin":true,"roleNames":""},"createDate":"2018-07-19 13:10:49","updateBy":{"id":"1","loginFlag":"1","admin":true,"roleNames":""},"updateDate":"2018-07-19 13:10:49","parentIds":"0,root1,","name":"家电清洗","sort":90,"hasChildren":false,"parentId":"root1"}]}
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
             * id : cf907d6b374c4f7da721367963768f06
             * remarks : 上门维修
             * createBy : {"id":"1","loginFlag":"1","admin":true,"roleNames":""}
             * createDate : 2018-07-19 13:11:34
             * updateBy : {"id":"1","loginFlag":"1","admin":true,"roleNames":""}
             * updateDate : 2018-07-19 13:18:02
             * parentIds : 0,root1,
             * name : 上门维修
             * sort : 120
             * hasChildren : false
             * parentId : root1
             */

            private String id;
            private String remarks;
            private CreateByBean createBy;
            private String createDate;
            private UpdateByBean updateBy;
            private String updateDate;
            private String parentIds;
            private String name;
            private int sort;
            private boolean hasChildren;
            private String parentId;

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
