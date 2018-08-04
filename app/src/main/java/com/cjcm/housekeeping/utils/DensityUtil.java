package com.cjcm.housekeeping.utils;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.util.TypedValue;


import com.cjcm.housekeeping.MyApp;

import java.lang.reflect.Method;


public class DensityUtil {

    public static int dip2px(float dpValue) {
        final float scale = MyApp.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int dip2sp(float dpValue) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, MyApp.getContext().getResources().getDisplayMetrics()));
    }

    public static int px2dip(float pxValue) {
        final float scale = MyApp.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2sp(float pxValue) {
        float fontScale = MyApp.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int sp2px(float spValue) {
        float fontScale = MyApp.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int sp2dip(float spValue) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, MyApp.getContext().getResources().getDisplayMetrics()));
    }

    /**
     * 获取设备是否存在NavigationBar
     *
     * @return hasNavigationBar
     */
    public static boolean hasNavigationBar() {
        boolean hasNavigationBar = false;
        Resources rs = MyApp.getContext().getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            @SuppressLint("PrivateApi") Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            @SuppressWarnings("unchecked") Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception ignored) {
        }
        return hasNavigationBar;
    }

    /**
     * 获取顶部statusBar高度
     *
     * @return px
     */
    public static int getStatusBarHeight() {
        Resources resources = MyApp.getContext().getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    /**
     * 获取底部navigationBar高度
     *
     * @return px
     */
    public static int getNavigationBarHeight() {
        Resources resources = MyApp.getContext().getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }
}

