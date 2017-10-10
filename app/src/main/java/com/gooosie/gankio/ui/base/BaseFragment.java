package com.gooosie.gankio.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gooosie.gankio.presenter.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * BaseFragment
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    protected Unbinder mUnbinder;
    protected T mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getViewLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initPresenter();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    protected abstract int getViewLayoutId();

    protected abstract void initPresenter();
}
