package com.cjcm.housekeeping.ui;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cjcm.housekeeping.Constants;
import com.cjcm.housekeeping.R;
import com.cjcm.housekeeping.base.BaseActivity;
import com.cjcm.housekeeping.base.BasePresenter;
import com.cjcm.housekeeping.utils.UiUtils;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import ren.perry.library.GlideMan;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/27
 */
public class BannerDetailActivity extends BaseActivity {

    @Bind(R.id.rootLL)
    LinearLayout rootLL;
    @Bind(R.id.tvTitle)
    TextView tvTitle;

    @Override
    protected void initStatusBar() {
        StatusBarUtil.setColor(this, UiUtils.getColor(R.color.white));
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_banner_detail;
    }

    @Override
    protected void initView() {
        tvTitle.setText("详情");
        List<String> imageList = new ArrayList<>();
        String imageAll = getIntent().getStringExtra("imageAll");
        while (imageAll.length() > 4) {
            String img = imageAll.substring(0, imageAll.indexOf(".") + 4);
            imageAll = imageAll.substring(img.length());
            imageList.add(Constants.Api.BASE_URL + img.replaceAll("\\|", ""));
        }

        for (String img : imageList) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            iv.setAdjustViewBounds(true);
            rootLL.addView(iv);
            new GlideMan.Builder().load(img).loadFailRes(R.mipmap.banner_error).into(iv);
        }

    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }


    @OnClick(R.id.ibBack)
    public void onViewClicked() {
        finish();
    }
}
