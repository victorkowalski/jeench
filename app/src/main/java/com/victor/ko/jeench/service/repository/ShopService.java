package com.victor.ko.jeench.service.repository;

import com.victor.ko.jeench.service.model.Responce;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ShopService {

    String API_BASE_URL = "https://api-dev.jeench.com/";

    @GET("v1/search-items")
    Call<Responce> getResponce();

    //simulate get shop details
    @GET("v1/search-items")
    Call<Responce> getShopDetails();

}
