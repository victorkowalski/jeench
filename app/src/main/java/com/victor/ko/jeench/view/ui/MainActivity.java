package com.victor.ko.jeench.view.ui;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.victor.ko.jeench.R;
import com.victor.ko.jeench.service.model.Item;
//import com.victor.ko.jeench.service.model.Message;
import com.victor.ko.jeench.service.model.Responce;
import com.victor.ko.jeench.service.model.Shop;
import com.victor.ko.jeench.service.repository.ShopAPI;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity /*implements HasSupportFragmentInjector */{


    private static String API_BASE_URL = "https://api-dev.jeench.com/";
    private Item item;
    //private Message message;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add project list fragment if this is first creation
        if (savedInstanceState == null) {
            ShopListFragment fragment = new ShopListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, ShopListFragment.TAG).commit();
        }

        //test gson
        //testGson();
        getShops();
    }

    /** Shows the shop detail fragment */
    public void show(Shop shop) {
        ShopFragment projectFragment = ShopFragment.forProject(shop.getShop_name());

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("project")
                .replace(R.id.fragment_container,
                        projectFragment, null).commit();
    }
/*
    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }*/

    private void getShops(){
        //final ProgressDialog loading = ProgressDialog.show(this,"Fetching Data","Please wait...",false,false);
/*
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        */
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

    private static Retrofit retrofit = null;
    //test
    public static Retrofit getClient(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Gson gson = new GsonBuilder().create();

        if(retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()/*GsonConverterFactory.create()*/)
                    .client(client)
                    .build();
        }
/*
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }*/
        return retrofit;
    }

    private void testGson(){
        String restaurantJson = "{'code':0,'message':[{'point_id':'7543','shop_id':'1212'}]}";

        Gson gson = new Gson();

        JSONArray jsonarray = null;
        try {
            jsonarray = new JSONArray(restaurantJson);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                String name = jsonobject.getString("name");
                String url = jsonobject.getString("url");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        CResponce restaurantObject = gson.fromJson(restaurantJson, CResponce.class);
        System.out.print(restaurantObject.getCode());
    }

    class CMessage{
        private String point_id;
        private String shop_id;

        public String getPoint_id() {
            return point_id;
        }

        public void setPoint_id(String point_id) {
            this.point_id = point_id;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }
    }
    class CResponce{
        private String code;
        private List<CMessage> cmessage = null;

        public List<CMessage> getCmessage() {
            return cmessage;
        }

        public void setCmessage(List<CMessage> cmessage) {
            this.cmessage = cmessage;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
