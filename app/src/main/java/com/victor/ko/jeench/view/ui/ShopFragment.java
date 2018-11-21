package com.victor.ko.jeench.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.victor.ko.jeench.R;
import com.victor.ko.jeench.databinding.FragmentShopDetailsBinding;
import com.victor.ko.jeench.di.Injectable;
import com.victor.ko.jeench.common.ImageService;
import com.victor.ko.jeench.service.model.Shop;
import com.victor.ko.jeench.viewmodel.ShopViewModel;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;


public class ShopFragment extends Fragment implements Injectable {
    private static final String KEY_SHOP_ID = "shop_id";
    private FragmentShopDetailsBinding binding;

    @Inject
    ImageService imageService;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        // Inflate this data binding layout
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop_details, container, false);

        // Create and set the adapter for the RecyclerView.
        return (View) binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ShopViewModel viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ShopViewModel.class);

        viewModel.setShopID(getArguments().getString(KEY_SHOP_ID));

        binding.setShopViewModel(viewModel);
        binding.setIsLoading(true);

        observeViewModel(viewModel);
    }

    private void observeViewModel(final ShopViewModel viewModel) {
        // Observe project data
        viewModel.getObservableShop().observe(this, new Observer<Shop>() {
            @Override
            public void onChanged(@Nullable Shop shop) {
                if (shop != null) {
                    binding.setIsLoading(false);

                    ImageView view = binding.shopImage;
                    imageService.loadImage(shop.getItem_image(), view);
                    /*Glide.with(view.getContext())
                            .load(shop.getItem_image())
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(view);*/

                    viewModel.setShop(shop);
                }
            }
        });
    }

    /** Creates project fragment for specific shop ID */
    public static ShopFragment forShop(String shopID) {
        ShopFragment fragment = new ShopFragment();
        Bundle args = new Bundle();

        args.putString(KEY_SHOP_ID, shopID);
        fragment.setArguments(args);

        return fragment;
    }
}
