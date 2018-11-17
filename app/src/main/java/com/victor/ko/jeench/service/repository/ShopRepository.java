package com.victor.ko.jeench.service.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import com.victor.ko.jeench.service.model.Item;
import com.victor.ko.jeench.service.model.Responce;
import com.victor.ko.jeench.service.model.Shop;
//example.test.mvvmsampleapp.service.model.Project;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class ShopRepository {
    private ShopService ShopService;

    @Inject
    public ShopRepository(ShopService ShopService) {
        this.ShopService = ShopService;
    }

    static String API_BASE_URL = "https://api-dev.jeench.com/";

    public LiveData<List<Shop>> getShopList() {
        final MutableLiveData<List<Shop>> data = new MutableLiveData<>();

        retrofit = getClient();
        ShopService shopService = retrofit.create(ShopService.class);

        Call<Responce> call = shopService.getResponce();

        //ShopService.getResponce()
        call.enqueue(new Callback<Responce>() {
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

/*   private void getShops(){
        //final ProgressDialog loading = ProgressDialog.show(this,"Fetching Data","Please wait...",false,false);
/*
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        */

/*
        retrofit = getClient();

        ShopAPI api = retrofit.create(ShopAPI.class);

        Call<Responce> call = api.getShops();
        call.enqueue(new Callback<Responce>() {
            @Override
            public void onResponse(Call<Responce> call, Response<Responce> response) {
                String code = response.body().getCode();
                List<Item> message = response.body().getMessage();

                System.out.println("address ::::::  " + code);
                System.out.println(message.get(3).getPoint_address());
                //ArrayList<Item> items = new ArrayList<>();
                //adapter = new PlanetAdapter(p,getApplicationContext());
                //recyclerView.setAdapter(adapter);
                //loading.dismiss();
            }

            @Override
            public void onFailure(Call<Responce> call, Throwable t) {
                //loading.dismiss();
                System.out.println(t.getLocalizedMessage());
            }
        });
    }

        */
        return data;
    }

    private static Retrofit retrofit = null;
    //test
    public static Retrofit getClient(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        //Gson gson = new GsonBuilder().create();

        if(retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }

        return retrofit;
    }

    public LiveData<Shop> getShopDetails(String shopName) {
        final MutableLiveData<Shop> data = new MutableLiveData<>();

        /*
        ShopService.getShopDetails(shopName).enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                simulateDelay();
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {
                // TODO better error handling in part #2 ...
                data.setValue(null);
            }
        });*/

        return data;
    }

    private void simulateDelay() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
