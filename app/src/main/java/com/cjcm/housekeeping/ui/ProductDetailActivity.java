package com.cjcm.housekeeping.ui;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cjcm.housekeeping.R;
import com.cjcm.housekeeping.adapter.ImageListRvAdapter;
import com.cjcm.housekeeping.base.BaseActivity;
import com.cjcm.housekeeping.base.BasePresenter;
import com.cjcm.housekeeping.bean.ProductBean;
import com.cjcm.housekeeping.loader.GlideImageLoader;
import com.cjcm.housekeeping.utils.ImageUtils;
import com.cjcm.housekeeping.utils.UiUtils;
import com.cjcm.housekeeping.view.FullyLinearLayoutManager;
import com.jaeger.library.StatusBarUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/28
 */
public class ProductDetailActivity extends BaseActivity {
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.bannerView)
    Banner bannerView;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvType)
    TextView tvType;
    @Bind(R.id.tvInfo)
    TextView tvInfo;
    @Bind(R.id.tvPrice)
    TextView tvPrice;
    @Bind(R.id.tvDanwei)
    TextView tvDanwei;
    @Bind(R.id.tvCount)
    TextView tvCount;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private ProductBean.BodyBean.PageBean.ListBean bean;
    private ImageListRvAdapter rvAdapter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected void initStatusBar() {
        StatusBarUtil.setColor(this, UiUtils.getColor(R.color.white));
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        tvTitle.setText("详情");
        bean = (ProductBean.BodyBean.PageBean.ListBean) getIntent().getSerializableExtra("bean");

        String priceStr = bean.getPrice();
        if (priceStr.contains("元")) {
            priceStr = priceStr.substring(0, priceStr.indexOf("元"));
        }

        String dw = bean.getWei().replaceAll("每", "");
        String dwStr = dw.length() > 0 ? "/" + dw : dw;

        tvName.setText(bean.getName());
        tvInfo.setText(bean.getTitle());
        tvType.setText("上门服务");
        tvPrice.setText(priceStr);
        tvCount.setText("50");
        tvDanwei.setText(dwStr);
        List<String> bannerImageList = ImageUtils.imageErgodic(bean.getImage());

        bannerView.setImageLoader(new GlideImageLoader());
        bannerView.setBannerAnimation(Transformer.Default);
        bannerView.setDelayTime(3000);
        bannerView.setIndicatorGravity(BannerConfig.CENTER);
        bannerView.setImages(bannerImageList);
        bannerView.start();

        List<String> contentImageList = ImageUtils.imageErgodic(bean.getContent());
        recyclerView.setFocusable(false);
        rvAdapter = new ImageListRvAdapter();
        rvAdapter.bindToRecyclerView(recyclerView);
        rvAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        FullyLinearLayoutManager lm = new FullyLinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(rvAdapter);
        rvAdapter.setNewData(contentImageList);
        View headerView = UiUtils.getView(R.layout.layout_toolbar_0);
        ((TextView) headerView.findViewById(R.id.tvTitle)).setText("图文详情");
        if (contentImageList.size() > 0) rvAdapter.addHeaderView(headerView);
        View footerView = UiUtils.getView(R.layout.layout_toolbar_0);
        ((TextView) footerView.findViewById(R.id.tvTitle)).setText("- End -");
        if (contentImageList.size() > 0) rvAdapter.addFooterView(footerView);
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        bannerView.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        bannerView.stopAutoPlay();
    }

    @OnClick({R.id.ibBack, R.id.tvClickOrder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibBack:
                finish();
                break;
            case R.id.tvClickOrder:
                startActivity(SaveOrderActivity.class, "bean", bean);
                overridePendingTransition(MOVE_LEFT);
                break;
        }
    }
}
