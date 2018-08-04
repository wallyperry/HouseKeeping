package com.cjcm.housekeeping.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cjcm.housekeeping.R;
import com.cjcm.housekeeping.utils.DensityUtil;

import java.io.Serializable;

import butterknife.ButterKnife;

/**
 * BaseFragment
 *
 * @author valentine
 * @date 2017/5/29
 */

@SuppressWarnings("unused")
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    /**
     * Activity过渡动画：向左推
     */
    public static final int MOVE_LEFT = 1;
    /**
     * Activity过渡动画：向右推
     */
    public static final int MOVE_RIGHT = -1;
    protected P mPresenter;

    /**
     * 宿主Activity
     */
    protected FragmentActivity activity;

    /**
     * 根View
     */
    protected View rootView;

    protected boolean mIsViewInitiated;
    protected boolean mIsVisibleToUser;
    protected boolean mIsDataInitiated;
    protected Bundle savedInstanceState;
    protected Toast toast;

    /**
     * 仅第一次可见时调用
     */
    protected void fetchData() {
    }

    /**
     * 设置跟布局的资源ID
     *
     * @return layout
     */
    protected abstract int setLayoutResourceId();

    /**
     * 创建Presenter
     *
     * @return P extends BasePresenter
     */
    protected abstract P onCreatePresenter();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = getActivity();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mIsViewInitiated = true;
        initFetchData();
    }

    protected void initFetchData() {
        if (mIsVisibleToUser && mIsViewInitiated && !mIsDataInitiated) {
            fetchData();
            mIsDataInitiated = true;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mIsVisibleToUser = isVisibleToUser;
        initFetchData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(setLayoutResourceId(), container, false);
        ButterKnife.bind(this, rootView);
        if (onCreatePresenter() != null) {
            mPresenter = onCreatePresenter();
        }
        this.savedInstanceState = savedInstanceState;
        initData(getArguments());
        initView();
        return rootView;
    }

    protected void toastShow(String s) {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(activity, s, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 初始化数据
     *
     * @param arguments 接受到的从其他地方传过来的参数
     */
    @SuppressWarnings("UnnecessaryReturnStatement")
    protected void initData(Bundle arguments) {
        if (arguments == null) {
            return;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
        ButterKnife.unbind(this);
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
        Intent intent = new Intent(activity, cla);
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
        Intent intent = new Intent(activity, cla);
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
        Intent intent = new Intent(activity, cla);
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
        Intent intent = new Intent(activity, cla);
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
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case MOVE_RIGHT:
                activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
        }
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
}
