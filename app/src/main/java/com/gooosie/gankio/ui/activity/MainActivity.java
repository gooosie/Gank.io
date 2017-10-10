package com.gooosie.gankio.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.gooosie.gankio.R;
import com.gooosie.gankio.presenter.GankPresenter;
import com.gooosie.gankio.ui.adapter.GankPagerAdapter;
import com.gooosie.gankio.ui.base.ToolBarActivity;
import com.gooosie.gankio.view.IView;

import butterknife.BindView;

/**
 * MainActivity
 */

public class MainActivity extends ToolBarActivity<GankPresenter> implements IView {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.container)
    ViewPager container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new GankPresenter(this, this);
        mPresenter.init();
    }

    @Override
    public void initView() {
        GankPagerAdapter gankPagerAdapter = new GankPagerAdapter(getSupportFragmentManager());
        container.setAdapter(gankPagerAdapter);
        container.setOffscreenPageLimit(4);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(container);
    }

    @Override
    protected boolean canBack() {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_about:
                mPresenter.gotoAboutActivity();
                return true;
            default:
                return false;
        }
    }
}
