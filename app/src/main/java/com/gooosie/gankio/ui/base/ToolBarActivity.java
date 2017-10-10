package com.gooosie.gankio.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.gooosie.gankio.R;
import com.gooosie.gankio.presenter.BasePresenter;

import butterknife.BindView;

/**
 * ToolBarActivity
 */

public abstract class ToolBarActivity<T extends BasePresenter> extends BaseActivity {
    protected ActionBar mActionBar;
    protected T mPresenter;

    @BindView(R.id.app_bar)
    protected AppBarLayout mAppBar;
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolBar();
    }

    protected void initToolBar() {
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(canBack());
        }
    }

    protected boolean canBack() {
        return true;
    }
}
