package com.gooosie.gankio.presenter;

import android.content.Context;

import com.gooosie.gankio.view.IView;

import io.reactivex.disposables.Disposable;

/**
 * BasePresenter
 */

public abstract class BasePresenter<T extends IView> implements IPresenter {

    protected Disposable mDisposable;
    protected T mView;
    protected Context mContext;

    public BasePresenter(Context context, T view) {
        mContext = context;
        mView = view;
    }

    @Override
    public void init() {
        mView.initView();
    }

    public abstract void release();
}
