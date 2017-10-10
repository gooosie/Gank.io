package com.gooosie.gankio.presenter;

import android.content.Context;
import android.content.Intent;

import com.gooosie.gankio.ui.activity.AboutActivity;
import com.gooosie.gankio.view.IView;

/**
 * GankPresenter
 */

public class GankPresenter extends BasePresenter<IView> {
    public GankPresenter(Context context, IView view) {
        super(context, view);
    }

    public void gotoAboutActivity() {
        mContext.startActivity(new Intent(mContext, AboutActivity.class));
    }

    @Override
    public void release() {

    }
}
