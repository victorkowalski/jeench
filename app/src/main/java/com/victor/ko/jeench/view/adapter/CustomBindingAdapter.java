package com.victor.ko.jeench.view.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class CustomBindingAdapter {
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }
    //test
    @BindingAdapter("imageUrl")
    public static void bindImage(ImageView imageView, String url) {
        Glide.with(fragment).load(url).into(imageView);
    }
}