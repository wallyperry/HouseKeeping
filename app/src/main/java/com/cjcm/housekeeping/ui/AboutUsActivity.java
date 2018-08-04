package com.cjcm.housekeeping.ui;

import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.cjcm.housekeeping.R;
import com.cjcm.housekeeping.base.BaseActivity;
import com.cjcm.housekeeping.bean.AboutUsBean;
import com.cjcm.housekeeping.mvp.contract.AboutContract;
import com.cjcm.housekeeping.mvp.presenter.AboutPresenter;
import com.cjcm.housekeeping.net.ApiException;
import com.cjcm.housekeeping.utils.UiUtils;
import com.jaeger.library.StatusBarUtil;

import butterknife.Bind;
import butterknife.OnClick;
import ren.perry.library.GlideMan;
import ren.perry.perry.LoadingDialog;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/28
 */
public class AboutUsActivity extends BaseActivity<AboutPresenter> implements AboutContract.View {
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.iv)
    ImageView iv;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvPhone)
    TextView tvPhone;
    @Bind(R.id.tvAddress)
    TextView tvAddress;
    @Bind(R.id.tvMail)
    TextView tvMail;

    //    private QMUITipDialog loadingDialog;
    private LoadingDialog loadingDialog;
    private String imageUrl = "http://www.perry.ren/source/hk_about_us.png";

    @Override
    protected void initStatusBar() {
        StatusBarUtil.setColor(this, UiUtils.getColor(R.color.white));
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initView() {
        tvTitle.setText("联系我们");
        loadingDialog = new LoadingDialog(this, "请稍候...");
        loadingDialog.setNotCancel();
        loadingDialog.show();

        new GlideMan.Builder().load(imageUrl).into(iv);
        mPresenter.aboutUs();
    }

    @Override
    protected AboutPresenter onCreatePresenter() {
        return new AboutPresenter(this);
    }

    @Override
    public void onAboutSuccess(AboutUsBean bean) {
        loadingDialog.dismiss();
        if (bean.isSuccess()) {
            tvName.setText(bean.getBody().getBaseinfo().getName());
            tvAddress.setText(bean.getBody().getBaseinfo().getAdress());
            tvPhone.setText(bean.getBody().getBaseinfo().getPhone());
            tvMail.setText(bean.getBody().getBaseinfo().getEmail());
        } else {
            showMsg(bean.getMsg());
        }

    }

    @Override
    public void onAboutError(ApiException.ResponseException e) {
        loadingDialog.dismiss();
        showMsg(e.message);
    }

    private void showMsg(String msg) {
        new MaterialDialog.Builder(this)
                .content(msg)
                .positiveText("好的")
                .show();
    }


    @OnClick(R.id.ibBack)
    public void onViewClicked() {
        finish();
    }
}
