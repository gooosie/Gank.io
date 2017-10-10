package com.gooosie.gankio.network;

import android.content.Context;

import com.gooosie.gankio.model.GankData;
import com.gooosie.gankio.presenter.BasePresenter;
import com.gooosie.gankio.view.IView;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * GankApi
 */

public interface GankApi {
    @GET("data/{type}/" + 20 + "/{page}")
    Observable<GankData> getGankData(@Path("type") String type, @Path("page") int page);
}
