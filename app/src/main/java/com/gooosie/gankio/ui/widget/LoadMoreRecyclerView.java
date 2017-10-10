package com.gooosie.gankio.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * LoadMoreRecyclerView
 */

public class LoadMoreRecyclerView extends RecyclerView {

    private boolean isScrollingToBottom = true;
    private LoadMoreListener mLoadMoreListener;

    public LoadMoreRecyclerView(Context context) {
        super(context);
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setmLoadMoreListener(LoadMoreListener loadMoreListener) {
        mLoadMoreListener = loadMoreListener;
    }

    @Override
    public void onScrollStateChanged(int state) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) getLayoutManager();
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            int lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition();
            int totalItemCount = layoutManager.getItemCount();
            if (lastVisibleItem == (totalItemCount - 1) && isScrollingToBottom) {
                if (mLoadMoreListener != null)
                    mLoadMoreListener.loadMore();
            }
        }
    }

    public interface LoadMoreListener {
        void loadMore();
    }
}
