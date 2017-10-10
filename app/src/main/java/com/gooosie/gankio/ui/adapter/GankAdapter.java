package com.gooosie.gankio.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gooosie.gankio.R;
import com.gooosie.gankio.model.Gank;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * GankAdapter
 */

public class GankAdapter extends RecyclerView.Adapter<GankAdapter.GankHolder> {

    List<Gank> mGankList;
    Context mContext;
    private static final String EXTRA_CUSTOM_TABS_SESSION = "android.support.customtabs.extra.SESSION";

    public GankAdapter(Context context, List<Gank> gankList) {
        mContext = context;
        mGankList = gankList;
    }

    @Override
    public GankHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gank, parent, false);
        return new GankHolder(view);
    }

    @Override
    public void onBindViewHolder(GankHolder holder, int position) {
        Gank gank = mGankList.get(position);
        holder.tvDesc.setTag(gank);
        holder.tvDesc.setText(gank.desc);
        holder.tvWho.setText("@" + gank.who);
    }

    @Override
    public int getItemCount() {
        return mGankList.size();
    }

    class GankHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_desc)
        TextView tvDesc;

        @BindView(R.id.tv_who)
        TextView tvWho;

        @OnClick(R.id.ll_gank)
        void toWebClick() {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(((Gank) tvDesc.getTag()).url));
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(mContext, Uri.parse(((Gank) tvDesc.getTag()).url));
        }

        public GankHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
