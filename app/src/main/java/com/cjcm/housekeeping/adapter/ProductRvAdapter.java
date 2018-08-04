package com.cjcm.housekeeping.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cjcm.housekeeping.R;
import com.cjcm.housekeeping.bean.ProductBean;
import com.cjcm.housekeeping.utils.ImageUtils;

import java.util.List;

import ren.perry.library.GlideMan;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/27
 */
public class ProductRvAdapter extends BaseQuickAdapter<ProductBean.BodyBean.PageBean.ListBean, BaseViewHolder> {
    public ProductRvAdapter() {
        super(R.layout.item_product);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductBean.BodyBean.PageBean.ListBean item) {

        List<String> imageList = ImageUtils.imageErgodic(item.getImage());

        new GlideMan.Builder()
                .load(imageList.get(0))
                .loadingRes(R.mipmap.banner_loading)
                .loadFailRes(R.mipmap.banner_error)
                .into(helper.getView(R.id.iv));

        String priceStr = item.getPrice();
        if (priceStr.contains("元")) {
            priceStr = priceStr.substring(0, priceStr.indexOf("元"));
        }

        String dw = item.getWei().replaceAll("每", "");
        String dwStr = dw.length() > 0 ? "/" + dw : dw;

        helper.setText(R.id.tvName, item.getName())
                .setText(R.id.tvInfo, item.getTitle())
                .setText(R.id.tvPrice, priceStr)
                .setText(R.id.tvCount, "50")
                .setText(R.id.tvDanwei, dwStr)
                .setText(R.id.tvType, "上门服务")
                .addOnClickListener(R.id.cv);
    }
}
