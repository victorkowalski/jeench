package com.victor.ko.jeench.service.repository;

import com.victor.ko.jeench.service.model.Responce;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ShopAPI {

    @GET("v1/search-items")
    Call<Responce> getShops();

}
