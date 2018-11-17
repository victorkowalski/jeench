package com.victor.ko.jeench.di;

import com.victor.ko.jeench.viewmodel.ShopListViewModel;
import com.victor.ko.jeench.viewmodel.ShopViewModel;

import dagger.Subcomponent;

/**
 * A sub component to create ViewModels. It is called by the
 * {@link com.victor.ko.jeench.viewmodel.ShopViewModelFactory}.
 */
@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    ShopListViewModel shopListViewModel();
    ShopViewModel shopViewModel();
}
