package com.mx.garland_view_ex.profile;


import android.view.View;
import android.widget.TextView;

import com.mx.garland_view_ex.R;
import com.mx.garland_view_ex.details.DetailsData;

import androidx.recyclerview.widget.RecyclerView;

public class ProfileItem extends RecyclerView.ViewHolder {

    public ProfileItem(View itemView) {
        super(itemView);
    }

    void setContent(DetailsData data) {
        ((TextView)itemView.findViewById(R.id.tv_title)).setText(data.title);
        ((TextView)itemView.findViewById(R.id.tv_text)).setText(data.text);
    }

}
