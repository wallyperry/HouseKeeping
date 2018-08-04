package com.cjcm.housekeeping.ui;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.cjcm.housekeeping.R;
import com.cjcm.housekeeping.base.BaseActivity;
import com.cjcm.housekeeping.bean.ProductBean;
import com.cjcm.housekeeping.bean.SaveOrderBean;
import com.cjcm.housekeeping.mvp.contract.SaveOrderContract;
import com.cjcm.housekeeping.mvp.presenter.SaveOrderPresenter;
import com.cjcm.housekeeping.net.ApiException;
import com.cjcm.housekeeping.utils.DateTimePickUtils;
import com.cjcm.housekeeping.utils.UiUtils;
import com.jaeger.library.StatusBarUtil;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/28
 */
public class SaveOrderActivity extends BaseActivity<SaveOrderPresenter> implements SaveOrderContract.View {
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvInfo)
    TextView tvInfo;
    @Bind(R.id.tvPrice)
    TextView tvPrice;
    @Bind(R.id.tvType)
    TextView tvType;
    @Bind(R.id.tvHint)
    TextView tvHint;
    @Bind(R.id.etPhone)
    EditText etPhone;
    @Bind(R.id.etAddress)
    EditText etAddress;
    @Bind(R.id.tvTime)
    TextView tvTime;
    @Bind(R.id.etRemark)
    EditText etRemark;

    private ProductBean.BodyBean.PageBean.ListBean bean;
    private QMUITipDialog loadingDialog;
    private QMUITipDialog successDialog;
    private QMUITipDialog errorDialog;

    @Override
    protected void initStatusBar() {
        StatusBarUtil.setColor(this, UiUtils.getColor(R.color.white));
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_save_order;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        tvTitle.setText("填写资料");
        bean = (ProductBean.BodyBean.PageBean.ListBean) getIntent().getSerializableExtra("bean");
        tvName.setText(bean.getName());
        tvInfo.setText(bean.getTitle());
        tvPrice.setText("¥" + bean.getPrice() + "元/" + bean.getWei().replaceAll("每", ""));
        tvType.setText("上门服务");
        tvHint.setText("服务价格以实际价格为准");
        tvTime.setText("请选择服务预约时间");

        loadingDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在提交")
                .create();

        successDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                .setTipWord("提交成功")
                .create();

        errorDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                .setTipWord("提交失败")
                .create();

        loadingDialog.setCancelable(false);
        successDialog.setCancelable(false);
        errorDialog.setCancelable(false);
    }

    @Override
    protected SaveOrderPresenter onCreatePresenter() {
        return new SaveOrderPresenter(this);
    }

    @OnClick({R.id.ibBack, R.id.tvTime, R.id.tvCommit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibBack:
                finish();
                break;
            case R.id.tvTime:
                new DateTimePickUtils(this, null, tvTime).dateTimePicKDialog();
                break;
            case R.id.tvCommit:
                commit();
                break;
        }
    }

    /**
     * 提交
     */
    private void commit() {
        String content = bean.getName() + "," + bean.getTitle();
        String phone = etPhone.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String time = tvTime.getText().toString().trim();
        if (phone.length() != 11) {
            toastShow("请输入11位手机号");
        } else if (address.length() < 1) {
            toastShow("请填写您的地址");
        } else if ("请选择服务预约时间".equals(time)) {
            toastShow("请选择服务预约时间");
        } else {
            mPresenter.saveOrder(content, phone, address, time, 0, etRemark.getText().toString().trim());
            loadingDialog.show();
        }
    }

    @Override
    public void onSaveSuccess(SaveOrderBean bean) {
        loadingDialog.dismiss();
        if (bean.isSuccess()) {
            successDialog.show();
            new Handler().postDelayed(() -> {
                successDialog.dismiss();
                new MaterialDialog.Builder(this)
                        .content(bean.getMsg() + "\n请耐心等待")
                        .positiveText("好的")
                        .onPositive((dialog, which) -> finish())
                        .cancelable(false)
                        .canceledOnTouchOutside(false)
                        .show();
            }, 1500);
        }

    }

    @Override
    public void onSaveError(ApiException.ResponseException e) {
        loadingDialog.dismiss();
        errorDialog.show();
        new Handler().postDelayed(() -> {
            successDialog.dismiss();
            new MaterialDialog.Builder(this)
                    .content(e.message)
                    .positiveText("好的")
                    .cancelable(false)
                    .canceledOnTouchOutside(false)
                    .show();
        }, 1500);
    }
}
