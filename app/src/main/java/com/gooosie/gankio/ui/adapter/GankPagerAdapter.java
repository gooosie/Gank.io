package com.gooosie.gankio.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gooosie.gankio.ui.fragment.GankFragment;

/**
 * GankPagerAdapter
 */

public class GankPagerAdapter extends FragmentStatePagerAdapter {

    String[] title = {"Android", "iOS", "前端", "瞎推荐", "拓展资源", "App"};

    public GankPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return GankFragment.newInstance(title[position]);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
