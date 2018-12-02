package com.victor.ko.jeench.service.repository;

import com.victor.ko.jeench.service.model.Responce;
import com.victor.ko.jeench.service.model.Shop;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class ShopRepository {
    private ShopService shopService;

    @Inject
    public ShopRepository(ShopService shopService) {
        this.shopService = shopService;
    }

    public LiveData<List<Shop>> getShopList() {
        final MutableLiveData<List<Shop>> data = new MutableLiveData<>();
//------------------------temp for debug responce----------------------------------------------------
        /*
        retrofit = getClient();
        ShopService shopService = retrofit.create(ShopService.class);
        Call<Responce> call = shopService.getResponce();
        call.enqueue(new Callback<Responce>() {
            */
//---------------------------------------------------------------------------
        shopService.getResponce().enqueue(new Callback<Responce>() {
            @Override
            public void onResponse(Call<Responce> call, Response<Responce> response) {
                data.setValue(response.body().getMessage() /*List of Shops*/);
            }

            @Override
            public void onFailure(Call<Responce> call, Throwable t) {
                // TODO better error handling...
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<Shop> getShopDetails(String shopId) {
        final MutableLiveData<Shop> data = new MutableLiveData<>();

        shopService.getShopDetails().enqueue(new Callback<Responce>() {
            @Override
            public void onResponse(Call<Responce> call, Response<Responce> response) {
                simulateDelay();
                //find shop by id
                Shop shop = getShopById(shopId, response.body());
                data.setValue(shop);
            }

            @Override
            public void onFailure(Call<Responce> call, Throwable t) {
                // TODO better error handling
                data.setValue(null);
            }
        });

        return data;
    }

    private Shop getShopById(String shopId, Responce responce){
        List<Shop> shops = responce.getMessage();
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
