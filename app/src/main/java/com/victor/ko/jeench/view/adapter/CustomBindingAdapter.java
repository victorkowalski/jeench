package com.victor.ko.jeench.view.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidx.databinding.BindingAdapter;

public class CustomBindingAdapter {
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("imageFromUrl")
    public static void bindImageFromUrl(ImageView view, String imageUrl) {
        if (!imageUrl.isEmpty()) {
            Glide.with(view.getContext())
                    .load(imageUrl)
                    //.transition(DrawableTransitionOptions.withCrossFade())
                    .into(view);
        }
    }
/*
    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }*/

    //test
/*
    @BindingAdapter("imageUrl")
    public static void bindImage(ImageView imageView, String url) {
        Glide.with(fragment).load(url).into(imageView);
    }*/
}