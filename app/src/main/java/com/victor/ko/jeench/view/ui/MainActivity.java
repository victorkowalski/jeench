package com.victor.ko.jeench.view.ui;

import android.os.Bundle;

import com.victor.ko.jeench.R;
import com.victor.ko.jeench.service.model.Shop;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add shop list fragment if this is first creation
        if (savedInstanceState == null) {
            ShopListFragment fragment = new ShopListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, ShopListFragment.TAG).commit();
        }
    }

    /** Shows the Shop detail fragment */
    public void show(Shop shop) {
        ShopFragment shopFragment = ShopFragment.forShop(shop.getShop_id());

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("shop")
                .replace(R.id.fragment_container,
                        shopFragment, null).commit();
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
