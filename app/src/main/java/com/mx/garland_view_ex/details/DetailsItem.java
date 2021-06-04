package com.mx.garland_view_ex.details;

import android.view.View;
import android.widget.TextView;

import com.mx.garland_view_ex.R;

import androidx.recyclerview.widget.RecyclerView;

class DetailsItem extends RecyclerView.ViewHolder {

    DetailsItem(View itemView) {
        super(itemView);
    }

    void setContent(DetailsData data) {
        ((TextView)itemView.findViewById(R.id.tv_title)).setText(data.title);
        ((TextView)itemView.findViewById(R.id.tv_text)).setText(data.text);
    }

}
