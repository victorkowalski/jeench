package com.victor.ko.jeench.viewmodel;

import android.app.Application;
import android.util.Log;

import com.victor.ko.jeench.service.model.Shop;
import com.victor.ko.jeench.service.repository.ShopRepository;
import com.victor.ko.jeench.service.repository.ShopRepositoryRx;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class ShopViewModel extends AndroidViewModel {
    private static final String TAG = ShopViewModel.class.getName();
    private static final MutableLiveData ABSENT = new MutableLiveData();
    {
        //noinspection unchecked
        ABSENT.setValue(null);
    }

    private final LiveData<Shop> shopObservable;
    private final MutableLiveData<String> shopID;

    public ObservableField<Shop> shop = new ObservableField<>();

    @Inject
    public ShopViewModel(@NonNull ShopRepositoryRx shopRepositoryRx, @NonNull Application application) {
        super(application);

        this.shopID = new MutableLiveData<>();
// get shop details using rx
        shopObservable = Transformations.switchMap(shopID, input -> {
            if (input.isEmpty()) {
                Log.i(TAG, "ShopViewModel shoptID is absent!!!");
                return ABSENT;
            }

            Log.i(TAG,"ShopViewModel shopID is " + shopID.getValue());

            return shopRepositoryRx.getShopDetails(shopID.getValue());
        });
    }
/*
    @Inject
    public ShopViewModel(@NonNull ShopRepository shopRepository, @NonNull Application application) {
        super(application);

        this.shopID = new MutableLiveData<>();

        shopObservable = Transformations.switchMap(shopID, input -> {
            if (input.isEmpty()) {
                Log.i(TAG, "ShopViewModel shoptID is absent!!!");
                return ABSENT;
            }

            Log.i(TAG,"ShopViewModel shopID is " + shopID.getValue());

            return shopRepository.getShopDetails(shopID.getValue());
        });
    }
*/
    public LiveData<Shop> getObservableShop() {
        return shopObservable;
    }

    public void setShop(Shop shop) {
        this.shop.set(shop);
    }

    public void setShopID(String shopID) {
        this.shopID.setValue(shopID);
    }
}
