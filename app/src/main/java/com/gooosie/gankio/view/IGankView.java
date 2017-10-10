package com.gooosie.gankio.view;

import com.gooosie.gankio.model.Gank;

import java.util.List;

/**
 * IGankView
 */

public interface IGankView extends IView {
    void showProgressBar();

    void hideProgressBar();

    void showNoMoreData();

    void showListView(List<Gank> results);

    void showErrorView();
}
