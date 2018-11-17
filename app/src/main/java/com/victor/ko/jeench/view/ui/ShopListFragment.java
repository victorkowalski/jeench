package com.victor.ko.jeench.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.victor.ko.jeench.R;
import com.victor.ko.jeench.di.Injectable;
import com.victor.ko.jeench.service.model.Shop;
import com.victor.ko.jeench.view.adapter.ShopAdapter;

import com.victor.ko.jeench.databinding.FragmentShopListBinding;
import com.victor.ko.jeench.view.callback.ShopClickCallback;
/*
import com.example.test.mvvmsampleapp.R;
import com.example.test.mvvmsampleapp.databinding.FragmentProjectListBinding;

import com.example.test.mvvmsampleapp.service.model.Project;
import com.example.test.mvvmsampleapp.view.adapter.ProjectAdapter;
import com.example.test.mvvmsampleapp.view.callback.ProjectClickCallback;
import com.example.test.mvvmsampleapp.viewmodel.ProjectListViewModel;
*/
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;
//import androidx.databinding.DataBindingUtil;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
//import androidx.lifecycle.ViewModelProviders;

public class ShopListFragment extends Fragment implements Injectable {
    public static final String TAG = "ShopListFragment";
    private ShopAdapter shopAdapter;
    private FragmentShopListBinding binding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop_list, container, false);

        shopAdapter = new ShopAdapter(projectClickCallback);
        binding.shopList.setAdapter(shopAdapter);
        binding.setIsLoading(true);

        return (View) binding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
/*
        final ProjectListViewModel viewModel = ViewModelProviders.of(this,
                viewModelFactory).get(ProjectListViewModel.class);

        observeViewModel(viewModel);*/
    }
/*
    private void observeViewModel(ProjectListViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getProjectListObservable().observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projects) {
                if (projects != null) {
                    binding.setIsLoading(false);
                    projectAdapter.setProjectList(projects);
                }
            }
        });
    }*/

    private final ShopClickCallback shopClickCallback = new ShopClickCallback() {
        @Override
        public void onClick(Shop shop) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) getActivity()).show(shop);
            }
        }
    };
}

