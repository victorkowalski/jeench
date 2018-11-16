package com.victor.ko.jeench.view.ui;

import android.os.Bundle;

import com.victor.ko.jeench.R;
import com.victor.ko.jeench.service.model.Item;
import com.victor.ko.jeench.service.model.Message;
import com.victor.ko.jeench.service.model.Responce;
import com.victor.ko.jeench.service.repository.ShopAPI;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity /*implements HasSupportFragmentInjector */{


    private String API_BASE_URL = "https://api-dev.jeench.com/v1/";
    private Item item;
    private Message message;

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

        getShops();
    }

    /** Shows the project detail fragment *//*
    public void show(Project project) {
        ProjectFragment projectFragment = ProjectFragment.forProject(project.name);

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("project")
                .replace(R.id.fragment_container,
                        projectFragment, null).commit();
    }*/
/*
    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }*/

    private void getShops(){
        //final ProgressDialog loading = ProgressDialog.show(this,"Fetching Data","Please wait...",false,false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(SimpleXmlConverterFactory.create()/*GsonConverterFactory.create()*/)
                .build();

        ShopAPI api = retrofit.create(ShopAPI.class);

        Call<Responce> call = api.getShops();
        call.enqueue(new Callback<Responce>() {
            @Override
            public void onResponse(Call<Responce> call, Response<Responce> response) {
                message = response.body().message;

                System.out.println("here");
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
}
