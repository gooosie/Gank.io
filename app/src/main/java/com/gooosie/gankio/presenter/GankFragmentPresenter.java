package com.gooosie.gankio.presenter;

import android.content.Context;

import com.gooosie.gankio.model.GankData;
import com.gooosie.gankio.network.GankClient;
import com.gooosie.gankio.view.IGankView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * GankFragmentPresenter
 */

public class GankFragmentPresenter extends BasePresenter<IGankView> {
    public GankFragmentPresenter(Context context, IGankView view) {
        super(context, view);
    }

    @Override
    public void release() {
        mDisposable.dispose();
    }

    public void loadGank(String type, int page) {
        mView.showProgressBar();
        mDisposable = GankClient.getGankRetrofitInstance().getGankData(type, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GankData>() {
                    @Override
                    public void accept(GankData gank) throws Exception {
                        mView.hideProgressBar();
                        if (gank.results.size() == 0) {
                            mView.showNoMoreData();
                        } else {
                            mView.showListView(gank.results);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.hideProgressBar();
                        mView.showErrorView();
                    }
                });
    }
}