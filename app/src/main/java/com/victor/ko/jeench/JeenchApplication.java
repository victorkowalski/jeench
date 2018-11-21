
package com.victor.ko.jeench;

import android.app.Activity;
import android.app.Application;
import android.widget.ImageView;
//import com.android.example.github.di.AppInjector
//import dagger.android.DispatchingAndroidInjector
//import dagger.android.HasActivityInjector
//import timber.log.Timber
import com.bumptech.glide.Glide;
import com.victor.ko.jeench.di.AppInjector;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class JeenchApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        AppInjector.init(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}