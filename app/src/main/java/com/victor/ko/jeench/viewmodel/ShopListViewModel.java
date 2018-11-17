package com.victor.ko.jeench.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.victor.ko.jeench.service.model.Shop;
import com.victor.ko.jeench.service.repository.ShopRepository;

import java.util.List;

import javax.inject.Inject;

public class ShopListViewModel extends AndroidViewModel {
    private final LiveData<List<Shop>> shopListObservable;

    @Inject
    public ShopListViewModel(@NonNull ShopRepository shopRepository, @NonNull Application application) {
        super(application);

        // If any transformation is needed, this can be simply done by Transformations class ...
        shopListObservable = shopRepository.getShopList();
    }

    /**
     * Expose the LiveData Shops query so the UI can observe it.
     */
    public LiveData<List<Shop>> getShopListObservable() {
        return shopListObservable;
    }
}