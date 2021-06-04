package com.mx.garland_view_ex.profile;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mx.garland_view_ex.R;
import com.mx.garland_view_ex.databinding.ProfileItemBinding;
import com.mx.garland_view_ex.details.DetailsData;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


class ProfileAdapter extends RecyclerView.Adapter<ProfileItem> {

    private final List<DetailsData> mData;

    public ProfileAdapter(final List<DetailsData> data) {
        super();
        mData = data;
    }

    @Override
    public ProfileItem onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final ProfileItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.profile_item, parent, false);
        return new ProfileItem(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ProfileItem holder, int position) {
        holder.setContent(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
