package com.cjcm.housekeeping.bean;

import lombok.Data;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/27
 */
@Data
public class FeedbackBean {

    /**
     * success : true
     * errorCode : -1
     * msg : 保存留言成功
     */

    private boolean success;
    private String errorCode;
    private String msg;
}
