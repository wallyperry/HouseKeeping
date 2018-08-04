package com.cjcm.housekeeping.api;


import com.cjcm.housekeeping.bean.AboutUsBean;
import com.cjcm.housekeeping.bean.FeedbackBean;
import com.cjcm.housekeeping.bean.HomeBean;
import com.cjcm.housekeeping.bean.ProductBean;
import com.cjcm.housekeeping.bean.ProductTypeBean;
import com.cjcm.housekeeping.bean.SaveOrderBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author perry
 * @date 2017/10/20
 * WeChat  917351143
 */

@SuppressWarnings("SpellCheckingInspection")
public interface ApiService {

    /**
     * 获取首页数据
     *
     * @return BannerBean
     */
    @POST("/housek/front/data/carouseData")
    Observable<HomeBean> homeData();


    /**
     * 获取产品类型
     *
     * @param parentId 上级ID
     * @return ProductTypeBean
     */
    @FormUrlEncoded
    @POST("/housek/front/data/getProtype")
    Observable<ProductTypeBean> productType(
            @Field("parent.id") String parentId);

    /**
     * 获取产品列表
     *
     * @param page 页数
     * @param size 每页数量
     * @param type 0：所有； 1：托管服务； 2：精品服务； 3：促销活动
     * @return ProductBean
     */
    @FormUrlEncoded
    @POST("/housek/front/data/productlist")
    Observable<ProductBean> productList(
            @Field("pageNo") int page,
            @Field("pageSize") int size,
            @Field("protype.id") String type);

    /**
     * 关于我们
     *
     * @return AboutUsBean
     */
    @POST("/housek/front/data/baseinfo")
    Observable<AboutUsBean> aboutUs();

    /**
     * 意见反馈
     *
     * @param phone 电话
     * @param msg   内容
     * @return FeedbackBean
     */
    @FormUrlEncoded
    @POST("/housek/front/data/savemessage")
    Observable<FeedbackBean> feedback(
            @Field("phone") String phone,
            @Field("message") String msg);

    /**
     * 订单确认
     *
     * @param content 服务内容
     * @param phone   电话
     * @param address 地址
     * @param time    时间
     * @param status  状态
     * @param remarks 测试
     * @return SaveOrderBean
     */
    @FormUrlEncoded
    @POST("/housek/front/data/saveOrder")
    Observable<SaveOrderBean> saveOrder(
            @Field("content") String content,
            @Field("tel") String phone,
            @Field("addressinfo") String address,
            @Field("time") String time,
            @Field("status") int status,
            @Field("remarks") String remarks);
}
