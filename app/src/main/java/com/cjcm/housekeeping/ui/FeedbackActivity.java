package com.cjcm.housekeeping.ui;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.cjcm.housekeeping.R;
import com.cjcm.housekeeping.base.BaseActivity;
import com.cjcm.housekeeping.bean.FeedbackBean;
import com.cjcm.housekeeping.mvp.contract.FeedbackContract;
import com.cjcm.housekeeping.mvp.presenter.FeedbackPresenter;
import com.cjcm.housekeeping.net.ApiException;
import com.cjcm.housekeeping.utils.UiUtils;
import com.jaeger.library.StatusBarUtil;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import butterknife.Bind;
import butterknife.OnClick;
import ren.perry.library.GlideMan;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/29
 */
public class FeedbackActivity extends BaseActivity<FeedbackPresenter> implements FeedbackContract.View {
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.iv)
    ImageView iv;
    @Bind(R.id.etPhone)
    EditText etPhone;
    @Bind(R.id.etContent)
    EditText etContent;

    private String imageUrl = "http://www.perry.ren/source/hk_feedback.jpg";
    private QMUITipDialog loadingDialog;

    @Override
    protected void initStatusBar() {
        StatusBarUtil.setColor(this, UiUtils.getColor(R.color.white));
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initView() {
        tvTitle.setText("意见反馈");
        new GlideMan.Builder().load(imageUrl).into(iv);
        loadingDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("请等待")
                .create();
    }

    @Override
    protected FeedbackPresenter onCreatePresenter() {
        return new FeedbackPresenter(this);
    }

    @OnClick({R.id.ibBack, R.id.tvCommit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibBack:
                finish();
                break;
            case R.id.tvCommit:
                commit();
                break;
        }
    }

    private void commit() {
        String phone = etPhone.getText().toString().trim();
        String content = etContent.getText().toString().trim();
        if (phone.length() != 11) {
            toastShow("请输入11位手机号");
        } else if (content.length() < 1) {
            toastShow("请输入您想说的话");
        } else {
            mPresenter.feedback(phone, content);
            loadingDialog.show();
        }
    }

    @Override
    public void onFeedbackSuccess(FeedbackBean bean) {
        loadingDialog.dismiss();
        new MaterialDialog.Builder(this)
                .content(bean.getMsg())
                .positiveText("好的")
                .onPositive((dialog, which) -> finish())
                .cancelable(false)
                .canceledOnTouchOutside(false)
                .show();
    }

    @Override
    public void onFeedbackError(ApiException.ResponseException e) {
        loadingDialog.dismiss();
        new MaterialDialog.Builder(this)
                .content(e.message)
                .positiveText("好的")
                .onPositive((dialog, which) -> finish())
                .cancelable(false)
                .canceledOnTouchOutside(false)
                .show();
    }
}
