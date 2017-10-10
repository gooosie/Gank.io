package com.gooosie.gankio.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gooosie.gankio.presenter.IPresenter;

import butterknife.ButterKnife;

/**
 * BaseActivity
 */

public abstract class BaseActivity<T extends IPresenter> extends AppCompatActivity {

    protected String TAG = this.getClass().getCanonicalName();
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        initPresenter();
    }

    protected abstract int getContentViewId();

    protected abstract void initPresenter();
}
