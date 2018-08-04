package com.cjcm.housekeeping.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.cjcm.housekeeping.R;
import com.cjcm.housekeeping.utils.DensityUtil;

import java.io.Serializable;

import butterknife.ButterKnife;

/**
 * BaseActivity
 *
 * @author valentine
 * @date 2017/5/29
 */

@SuppressWarnings("unused")
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    /**
     * Activity过渡动画：向左推
     */
    public static final int MOVE_LEFT = 1;
    /**
     * Activity过渡动画：向右推
     */
    public static final int MOVE_RIGHT = -1;
    protected P mPresenter;
    protected Context context;
    protected Bundle savedInstanceState;
    protected Toast toast;
    /**
     * 是否使用Finish动画，默认打开
     */
    private boolean finishAnim;

    /**
     * 初始化布局
     *
     * @return layout
     */
    protected abstract int initLayoutId();

    /**
     * 执行逻辑代码
     */
    protected abstract void initView();

    /**
     * 设置状态栏
     * QMUIStatusBarHelper.translucent(this);
     * QMUIStatusBarHelper.setStatusBarLightMode(this);
     */
    protected void initStatusBar() {
    }

    /**
     * 创建Presenter
     *
     * @return P extend BasePresenter
     */
    protected abstract P onCreatePresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        initLayoutId();
        super.onCreate(savedInstanceState);
        if (onCreatePresenter() != null) {
            mPresenter = onCreatePresenter();
        }
        setContentView(initLayoutId());
        context = this;
        ButterKnife.bind(this);
        finishAnim = true;
        initStatusBar();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
        ButterKnife.unbind(this);
    }

    /**
     * 显示一个Toast
     *
     * @param s txt
     */
    protected void toastShow(@NonNull String s) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * 取消Toast
     */
    protected void toastCancel() {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
    }

    /**
     * 启动一个Activity
     *
     * @param cla activity
     */
    protected void startActivity(@NonNull Class<? extends BaseActivity> cla) {
        startActivity(cla, null);
    }

    /**
     * 启动一个Activity，并传递一个Bundle
     *
     * @param cla activity
     * @param b   bundle
     */
    protected void startActivity(@NonNull Class<? extends BaseActivity> cla, Bundle b) {
        Intent intent = new Intent(this, cla);
        if (b != null) intent.putExtras(b);
        startActivity(intent);
    }

    /**
     * 启动一个Activity，并传递一个Int值
     *
     * @param cla   activity
     * @param key   key
     * @param value value
     */
    protected void startActivity(@NonNull Class<? extends BaseActivity> cla, String key, int value) {
        Intent intent = new Intent(this, cla);
        intent.putExtra(key, value);
        startActivity(intent);
    }

    /**
     * 启动一个Activity，并传递一个String值
     *
     * @param cla   activity
     * @param key   key
     * @param value value
     */
    protected void startActivity(@NonNull Class<? extends BaseActivity> cla, String key, String value) {
        Intent intent = new Intent(this, cla);
        intent.putExtra(key, value);
        startActivity(intent);
    }

    /**
     * 启动一个Activity，并传递一个Serializable值
     *
     * @param cla          activity
     * @param key          key
     * @param serializable value
     */
    protected void startActivity(@NonNull Class<? extends BaseActivity> cla, String key, Serializable serializable) {
        Intent intent = new Intent(this, cla);
        intent.putExtra(key, serializable);
        startActivity(intent);
    }

    /**
     * 启用过渡动画
     *
     * @param move MOVE_LEFT：向左推←
     *             MOVE_RIGHT：向右推→
     */
    protected void overridePendingTransition(int move) {
        switch (move) {
            case MOVE_LEFT:
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case MOVE_RIGHT:
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
        }
    }

    /**
     * 导航栏半透明（全屏）
     */
    protected void translucentNavigation() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    /**
     * 状态栏半透明（半透明）
     */
    protected void translucentStatus() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    /**
     * 按返回键默认Finish
     */
    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        if (finishAnim) overridePendingTransition(MOVE_RIGHT);
    }

    /**
     * 关闭Finish动画
     */
    protected void closeFinishAnim() {
        finishAnim = false;
    }

    /**
     * 设置全屏
     */
    protected void setFullScreen() {
        getWindow().setFlags(0x00000400, 0x00000400);
        requestWindowFeature(1);
    }

    /**
     * 状态栏高度
     *
     * @return px
     */
    protected int navHeight() {
        return DensityUtil.getNavigationBarHeight();
    }

    /**
     * 导航栏高度
     *
     * @return px
     */
    protected int statusHeight() {
        return DensityUtil.getStatusBarHeight();
    }

    /**
     * 是否有Navigation
     *
     * @return boolean
     */
    protected boolean hasNavBar() {
        return DensityUtil.hasNavigationBar();
    }


    /**
     * 设置Margins
     *
     * @param v view
     * @param l left
     * @param t top
     * @param r right
     * @param b bottom
     */
    protected void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }
}