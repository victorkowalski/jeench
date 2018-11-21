package com.victor.ko.jeench.common;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageService {

    public void loadImage(String url, ImageView imageView) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
