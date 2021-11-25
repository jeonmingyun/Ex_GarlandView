package com.tensun.garlandviewdemo.main.outer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ramotion.garlandview.TailAdapter;
import com.tensun.garlandviewdemo.R;
import com.tensun.garlandviewdemo.main.MainActivity;
import com.tensun.garlandviewdemo.main.inner.InnerData;

import java.util.List;

public class OuterAdapter extends TailAdapter<OuterItem> {

    private final int POOL_SIZE = 16;

    private List<List<InnerData>> mData;
    private final RecyclerView.RecycledViewPool mPool;
    private MainActivity mainAct;

    public OuterAdapter(List<List<InnerData>> data, MainActivity mainAct) {
        this.mData = data;
        this.mainAct = mainAct;

        mPool = new RecyclerView.RecycledViewPool();
        mPool.setMaxRecycledViews(0, POOL_SIZE);
    }

    /** 創建Item的View */
    @Override
    public OuterItem onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new OuterItem(view, mPool, mainAct);
    }

    @Override
    public void onBindViewHolder(OuterItem holder, int position) {
        holder.setContent(mData.get(position));
    }

    public void update(List<List<InnerData>> outerData) {
        this.mData = outerData;
        notifyDataSetChanged();
    }

    @Override
    public void onViewRecycled(OuterItem holder) {
        holder.clearContent();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.outer_item;
    }
}
