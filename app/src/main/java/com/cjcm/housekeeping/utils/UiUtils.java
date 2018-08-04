package com.cjcm.housekeeping.utils;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.cjcm.housekeeping.MyApp;
import com.cjcm.housekeeping.R;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/26
 */
public class UiUtils {
    public static int getColor(int id) {
        return MyApp.getContext().getResources().getColor(id);
    }

    public static int getAppColor() {
        return getColor(R.color.app_color);
    }

    @NonNull
    public static String getString(int id) {
        return MyApp.getContext().getResources().getString(id);
    }

    @NonNull
    public static String[] getStringArray(int id) {
        return MyApp.getContext().getResources().getStringArray(id);
    }

    @NonNull
    public static int[] getIntArray(int id) {
        return MyApp.getContext().getResources().getIntArray(id);
    }

    public static View getView(int id) {
        return LayoutInflater.from(MyApp.getContext()).inflate(id, null);
    }

    public static Drawable getDrawable(int id) {
        return MyApp.getContext().getResources().getDrawable(id);
    }

    public static Animation getAnimation(int id) {
        return AnimationUtils.loadAnimation(MyApp.getContext(), id);
    }

    public static View getRvEmptyView() {
        return getView(R.layout.view_rv_empty);
    }
}
