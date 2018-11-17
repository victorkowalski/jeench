package com.victor.ko.jeench.di;

import com.victor.ko.jeench.view.ui.ShopFragment;
import com.victor.ko.jeench.view.ui.ShopListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract ShopFragment contributeShopFragment();

    @ContributesAndroidInjector
    abstract ShopListFragment contributeShopListFragment();
}
