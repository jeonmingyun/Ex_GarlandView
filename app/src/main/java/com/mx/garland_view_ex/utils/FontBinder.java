package com.mx.garland_view_ex.utils;


import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

public class FontBinder {

    @BindingAdapter("bind:font")
    public static void setTypeface(TextView textView, String fontName) {
        final AssetManager assets = textView.getContext().getAssets();
        textView.setTypeface(Typeface.createFromAsset(assets, "fonts/" + fontName));
    }

}
