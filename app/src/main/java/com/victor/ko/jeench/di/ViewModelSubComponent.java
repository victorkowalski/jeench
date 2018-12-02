package com.victor.ko.jeench.di;

import com.victor.ko.jeench.viewmodel.ShopListViewModel;
import com.victor.ko.jeench.viewmodel.ShopViewModel;

import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    ShopListViewModel shopListViewModel();
    ShopViewModel shopViewModel();
}
