package com.victor.ko.jeench.service.repository;

import com.victor.ko.jeench.service.model.Responce;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ShopServiceRx {

    String API_BASE_URL = "https://api-dev.jeench.com/";

    //get items with rxjava
    @GET("v1/search-items")
    Observable<Responce> getShopDetails();

}
