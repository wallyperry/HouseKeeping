package com.cjcm.housekeeping.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.cjcm.housekeeping.Constants;
import com.cjcm.housekeeping.R;
import com.cjcm.housekeeping.base.BaseActivity;
import com.cjcm.housekeeping.bean.HomeBean;
import com.cjcm.housekeeping.loader.GlideImageLoader;
import com.cjcm.housekeeping.mvp.contract.HomeContract;
import com.cjcm.housekeeping.mvp.presenter.HomePresenter;
import com.cjcm.housekeeping.net.ApiException;
import com.cjcm.housekeeping.utils.UiUtils;
import com.jaeger.library.StatusBarUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observable;

public class MainActivity extends BaseActivity<HomePresenter> implements HomeContract.View {

    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.bannerTopView)
    Banner bannerTopView;
    @Bind(R.id.bannerCenterView)
    Banner bannerCenterView;
    @Bind(R.id.bannerBottomView)
    Banner bannerBottomView;
    @Bind(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    private int count = 1;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initStatusBar() {
        StatusBarUtil.setColor(this, UiUtils.getColor(R.color.white));
    }

    @Override
    protected void initView() {
        closeFinishAnim();
        tvTitle.setText("伊宁捷洁家政");

        refreshLayout.setColorSchemeColors(UiUtils.getAppColor());
        refreshLayout.setOnRefreshListener(this::initData);
        refreshLayout.post(() -> {
            refreshLayout.setRefreshing(true);
            initData();
        });

        initBannerView();
    }

    private void initBannerView() {
        bannerTopView.setImageLoader(new GlideImageLoader());
        bannerTopView.setBannerAnimation(Transformer.Accordion);
        bannerTopView.setDelayTime(3000);
        bannerTopView.setIndicatorGravity(BannerConfig.CENTER);

        bannerCenterView.setImageLoader(new GlideImageLoader());
        bannerCenterView.setBannerAnimation(Transformer.Accordion);
        bannerCenterView.setDelayTime(4000);

        bannerBottomView.setImageLoader(new GlideImageLoader());
        bannerBottomView.setBannerAnimation(Transformer.Accordion);
        bannerBottomView.setDelayTime(5000);
    }


    private void initData() {
        mPresenter.getHomeData();
    }

    @Override
    protected HomePresenter onCreatePresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void onSuccess(HomeBean bean) {
        refreshLayout.setRefreshing(false);
        setBannerData(bean.getBody().getData());
    }

    private void setBannerData(List<HomeBean.BodyBean.DataBean> data) {
        List<String> bannerTopImg = new ArrayList<>();
        List<String> bannerCenterImg = new ArrayList<>();
        List<String> bannerBottomImg = new ArrayList<>();

        List<String> topContent = new ArrayList<>();
        List<String> centerContent = new ArrayList<>();
        List<String> bottomContent = new ArrayList<>();
        for (HomeBean.BodyBean.DataBean b : data) {
            switch (b.getType()) {
                case "1":
                    bannerTopImg.add(Constants.Api.BASE_URL + b.getImage());
                    topContent.add(b.getContent());
                    break;
                case "2":
                    bannerCenterImg.add(Constants.Api.BASE_URL + b.getImage());
                    centerContent.add(b.getContent());
                    break;
                case "3":
                    bannerBottomImg.add(Constants.Api.BASE_URL + b.getImage());
                    bottomContent.add(b.getContent());
                    break;
            }
        }
        bannerTopView.setImages(bannerTopImg).setOnBannerListener(position -> {
            startActivity(BannerDetailActivity.class, "imageAll", topContent.get(position));
            overridePendingTransition(MOVE_LEFT);
        }).start();
        bannerCenterView.setImages(bannerCenterImg).setOnBannerListener(position -> {
            startActivity(BannerDetailActivity.class, "imageAll", centerContent.get(position));
            overridePendingTransition(MOVE_LEFT);
        }).start();
        bannerBottomView.setImages(bannerBottomImg).setOnBannerListener(position -> {
            startActivity(BannerDetailActivity.class, "imageAll", bottomContent.get(position));
            overridePendingTransition(MOVE_LEFT);
        }).start();
    }

    @Override
    public void onError(ApiException.ResponseException e) {
        refreshLayout.setRefreshing(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bannerTopView.startAutoPlay();
        bannerCenterView.startAutoPlay();
        bannerBottomView.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        bannerTopView.stopAutoPlay();
        bannerCenterView.stopAutoPlay();
        bannerBottomView.stopAutoPlay();
    }

    @OnClick({R.id.jjtsLL, R.id.jzfwLL, R.id.gywmLL, R.id.yjfkLL})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jjtsLL:   //促销活动
                startActivity(ProductTypeActivity.class, "id", Constants.TypeId.Jjts);
                overridePendingTransition(MOVE_LEFT);
                break;
            case R.id.jzfwLL:   //家政服务
                startActivity(ProductTypeActivity.class, "id", Constants.TypeId.Jzfw);
                overridePendingTransition(MOVE_LEFT);
                break;
            case R.id.gywmLL:   //关于我们
                startActivity(AboutUsActivity.class);
                overridePendingTransition(MOVE_LEFT);
                break;
            case R.id.yjfkLL:   //意见反馈
                startActivity(FeedbackActivity.class);
                overridePendingTransition(MOVE_LEFT);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        int exitCount = 2;
        if (count != exitCount) {
            toastShow("再按一次退出");
        } else {
            System.exit(0);
        }
        count = 2;
        Observable.timer(2, TimeUnit.SECONDS).subscribe(aLong -> count = 1);
    }
}
