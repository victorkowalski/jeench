package com.victor.ko.jeench.service.repository;

import android.util.Log;

import com.victor.ko.jeench.service.model.Shop;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class ShopRepositoryRx {
    private ShopServiceRx shopServiceRx;
    private Disposable shopDetailsisposable;

    @Inject
    public ShopRepositoryRx(ShopServiceRx shopServiceRx) {
        this.shopServiceRx = shopServiceRx;
    }

    public LiveData<Shop> getShopDetails(String shopId) {
        final MutableLiveData<Shop> data = new MutableLiveData<>();

        shopDetailsisposable = shopServiceRx.getShopDetails()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    Log.d("shop details", "all shops");
                    Shop shop = getShopById(shopId, response.getMessage());
                    data.setValue(shop);

                }, throwable -> Log.e("error", throwable.getMessage()));

        return data;
    }

    private Shop getShopById(String shopId, List<Shop> shops){
        for(Shop shop: shops){
            if(shop.getShop_id().equals(shopId)){
                return shop;
            }
        }
        return null;
    }

    private void simulateDelay() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
