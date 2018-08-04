package com.cjcm.housekeeping;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cjcm.housekeeping.base.BaseActivity;
import com.cjcm.housekeeping.base.BasePresenter;
import com.cjcm.housekeeping.ui.MainActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/26
 */
public class SplashActivity extends BaseActivity {

    private final long count = 4;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.tvSkip)
    TextView tvSkip;
    @Bind(R.id.contentLayout)
    RelativeLayout contentLayout;
    private Subscription subscribe;

    @Override
    protected int initLayoutId() {
        if (!isTaskRoot()) {
            Intent i = getIntent();
            String action = i.getAction();
            if (i.hasCategory(Intent.CATEGORY_APP_CALENDAR)
                    && !TextUtils.isEmpty(action)
                    && action.equals(Intent.ACTION_MAIN)) {
                finish();
            }
        }
        setTheme(R.style.AppTheme);
        return R.layout.activity_splash;
    }

    @Override
    protected void initStatusBar() {
        translucentNavigation();
        translucentStatus();
    }

    @Override
    protected void initView() {
        closeFinishAnim();
        setMargins(contentLayout, 0, statusHeight(), 0, hasNavBar() ? navHeight() : 0);
        subscribe = Observable.interval(1, TimeUnit.SECONDS).take((int) (count + 1))
                .map(aLong -> count - aLong)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(() -> {
                    if (!SplashActivity.this.isFinishing()) {
                        if (subscribe != null) {
                            subscribe.unsubscribe();
                        }
                        startActivity(MainActivity.class);
                        overridePendingTransition(MOVE_LEFT);
                    }
                })
                .doOnSubscribe(() -> {
                    tvSkip.setVisibility(View.VISIBLE);
                    String s = count + 1 + " s 跳过";
                    tvSkip.setText(s);
                })
                .subscribe(aLong -> {
                    String txt = aLong + " s 跳过";
                    tvSkip.setText(txt);
                });
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (subscribe != null) {
            subscribe.unsubscribe();
        }
        finish();
    }

    @Override
    public void onBackPressed() {
    }

    @OnClick({R.id.tvSkip})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.tvSkip:
                startActivity(MainActivity.class);
                overridePendingTransition(MOVE_LEFT);
                break;
        }
    }
}
