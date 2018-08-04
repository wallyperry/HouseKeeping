package com.cjcm.housekeeping.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import lombok.Setter;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/27
 */
public class PageAdapter extends FragmentPagerAdapter{
    @Setter
    private List<Fragment> fragments;
    @Setter
    private List<String> titles;

    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
