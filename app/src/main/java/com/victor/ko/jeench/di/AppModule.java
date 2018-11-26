package com.victor.ko.jeench.di;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.victor.ko.jeench.common.ImageService;
import com.victor.ko.jeench.service.repository.ShopService;
import com.victor.ko.jeench.service.repository.ShopServiceRx;
import com.victor.ko.jeench.viewmodel.ShopViewModelFactory;

import javax.inject.Singleton;

import androidx.lifecycle.ViewModelProvider;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(subcomponents = ViewModelSubComponent.class)
class AppModule {
    @Singleton @Provides
    ShopService provideShopService() {
        return new Retrofit.Builder()
                .baseUrl(ShopService.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ShopService.class);
    }

    @Singleton @Provides
    ShopServiceRx provideShopServiceRx() {
        return new Retrofit.Builder()
                .baseUrl(ShopServiceRx.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ShopServiceRx.class);
    }

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(
            ViewModelSubComponent.Builder viewModelSubComponent) {

        return new ShopViewModelFactory(viewModelSubComponent.build());
    }

    @Singleton
    @Provides
    ImageService imageService() {
        return new ImageService();
    }
}
