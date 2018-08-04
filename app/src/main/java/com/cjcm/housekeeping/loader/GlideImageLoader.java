package com.cjcm.housekeeping.loader;

import android.content.Context;
import android.widget.ImageView;

import com.cjcm.housekeeping.R;
import com.youth.banner.loader.ImageLoader;

import ren.perry.library.GlideMan;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/19
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        String imageUrl = (String) path;
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        new GlideMan.Builder()
                .load(imageUrl)
                .loadingRes(R.mipmap.banner_loading)
                .loadFailRes(R.mipmap.banner_error)
                .into(imageView);
    }
}
