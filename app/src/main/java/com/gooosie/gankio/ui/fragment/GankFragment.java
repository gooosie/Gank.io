package com.gooosie.gankio.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.gooosie.gankio.R;
import com.gooosie.gankio.model.Gank;
import com.gooosie.gankio.presenter.GankFragmentPresenter;
import com.gooosie.gankio.ui.adapter.GankAdapter;
import com.gooosie.gankio.ui.base.BaseFragment;
import com.gooosie.gankio.ui.widget.GankDecoration;
import com.gooosie.gankio.ui.widget.LoadMoreRecyclerView;
import com.gooosie.gankio.view.IGankView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * GankFragment
 */

public class GankFragment extends BaseFragment<GankFragmentPresenter> implements IGankView, SwipeRefreshLayout.OnRefreshListener, LoadMoreRecyclerView.LoadMoreListener {

    private static final String TYPE = "type";
    private String type;

    private List<Gank> mGankList;
    private GankAdapter mAdapter;
    private int page = 1;
    private boolean isRefresh = true;
    private boolean canLoading = true;

    @BindView(R.id.recycler_view)
    LoadMoreRecyclerView mLoadMoreRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    public GankFragment() {

    }

    public static GankFragment newInstance(String type) {
        GankFragment fragment = new GankFragment();
        Bundle args = new Bundle();
        args.putString(TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            type = args.getString(TYPE);
        }
    }

    @Override
    protected int getViewLayoutId() {
        return R.layout.fragment_gank;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new GankFragmentPresenter(getContext(), this);
        mPresenter.init();
    }

    @Override
    public void initView() {
        mGankList = new ArrayList<>();
        mAdapter = new GankAdapter(getContext(), mGankList);
        mLoadMoreRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mLoadMoreRecyclerView.addItemDecoration(new GankDecoration());
        mLoadMoreRecyclerView.setAdapter(mAdapter);
        mLoadMoreRecyclerView.setmLoadMoreListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                mPresenter.loadGank(type, page);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.release();
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        page = 1;
        mPresenter.loadGank(type, page);
    }

    @Override
    public void loadMore() {
        if (canLoading) {
            mPresenter.loadGank(type, page);
            canLoading = false;
        }
    }

    @Override
    public void showProgressBar() {
        if (!mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void hideProgressBar() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showNoMoreData() {
        canLoading = false;
    }

    @Override
    public void showListView(List<Gank> results) {
        canLoading = true;
        page++;
        if (isRefresh) {
            mGankList.clear();
            mGankList.addAll(results);
            mAdapter.notifyDataSetChanged();
            isRefresh = false;
        } else {
            mGankList.addAll(results);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showErrorView() {
        canLoading = true;
    }
}

