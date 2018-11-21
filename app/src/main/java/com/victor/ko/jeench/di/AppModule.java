package com.victor.ko.jeench.di;

import com.victor.ko.jeench.common.ImageService;
import com.victor.ko.jeench.service.repository.ShopService;
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
