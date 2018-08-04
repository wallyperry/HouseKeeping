package com.cjcm.housekeeping.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.cjcm.housekeeping.Constants;
import com.cjcm.housekeeping.R;
import com.cjcm.housekeeping.adapter.PageAdapter;
import com.cjcm.housekeeping.base.BaseActivity;
import com.cjcm.housekeeping.bean.ProductTypeBean;
import com.cjcm.housekeeping.mvp.contract.ProductTypeConstract;
import com.cjcm.housekeeping.mvp.presenter.ProductTypePresenter;
import com.cjcm.housekeeping.net.ApiException;
import com.cjcm.housekeeping.utils.UiUtils;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 产品类别
 *
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/26
 */
public class ProductTypeActivity extends BaseActivity<ProductTypePresenter> implements ProductTypeConstract.View {
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    private String currentId;
    private String currentType;

    private List<String> mTitles;
    private List<Fragment> mFragments;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_product_type;
    }

    @Override
    protected void initStatusBar() {
        StatusBarUtil.setColor(this, UiUtils.getColor(R.color.white));
    }

    @Override
    protected void initView() {
        switch (getIntent().getStringExtra("id")) {
            case Constants.TypeId.Jjts:
                currentId = Constants.TypeId.Jjts;
                currentType = Constants.TypeId.Jjts_Info;
                break;
            case Constants.TypeId.Jzfw:
                currentId = Constants.TypeId.Jzfw;
                currentType = Constants.TypeId.Jzfw_Info;
                break;
        }
        tvTitle.setText(currentType);
        mFragments = new ArrayList<>();
        mTitles = new ArrayList<>();
        mPresenter.productType(currentId);
    }

    @Override
    protected ProductTypePresenter onCreatePresenter() {
        return new ProductTypePresenter(this, this);
    }

    @OnClick({R.id.ibBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibBack:
                finish();
                break;
        }
    }

    @Override
    public void onProductTypeSuccess(ProductTypeBean bean) {
        if (bean.isSuccess()) {
            loadType(bean.getBody().getData());
        } else {
            showErrorMsgAndFinish("获取数据失败：" + bean.getMsg());
        }
    }

    private void loadType(List<ProductTypeBean.BodyBean.DataBean> data) {
        for (ProductTypeBean.BodyBean.DataBean b : data) {
            mTitles.add(b.getName());
            mFragments.add(ProductTypeFragment.newInstance(b.getId()));
        }
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager());
        pageAdapter.setTitles(mTitles);
        pageAdapter.setFragments(mFragments);
        viewPager.setAdapter(pageAdapter);
        viewPager.setOffscreenPageLimit(mTitles.size() - 1);
        tabLayout.setTabMode(TabLayout.GRAVITY_CENTER);
        tabLayout.setTabTextColors(UiUtils.getColor(R.color.color_5), UiUtils.getColor(R.color.app_color));
        tabLayout.setSelectedTabIndicatorColor(UiUtils.getColor(R.color.app_color));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onProductTypeError(ApiException.ResponseException e) {
        showErrorMsgAndFinish("获取数据失败：" + e.message);
    }

    private void showErrorMsgAndFinish(String msg) {
        new MaterialDialog.Builder(this)
                .content(msg + "\n请稍后再试")
                .positiveText("知道了")
                .onPositive((dialog, which) -> finish())
                .show();
    }
}
