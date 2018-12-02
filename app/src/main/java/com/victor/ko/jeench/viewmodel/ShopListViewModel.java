package com.victor.ko.jeench.viewmodel;

import android.app.Application;

import com.victor.ko.jeench.service.model.Shop;
import com.victor.ko.jeench.service.repository.ShopRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ShopListViewModel extends AndroidViewModel {
    private final LiveData<List<Shop>> shopListObservable;

    @Inject
    public ShopListViewModel(@NonNull ShopRepository shopRepository, @NonNull Application application) {
        super(application);

        shopListObservable = shopRepository.getShopList();
    }

    public LiveData<List<Shop>> getShopListObservable() {
        return shopListObservable;
    }
}