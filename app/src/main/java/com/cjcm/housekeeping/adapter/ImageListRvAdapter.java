package com.cjcm.housekeeping.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cjcm.housekeeping.R;

import ren.perry.library.GlideMan;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/28
 */
public class ImageListRvAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ImageListRvAdapter() {
        super(R.layout.item_image_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        new GlideMan.Builder().load(item).into(helper.getView(R.id.iv));
    }
}
