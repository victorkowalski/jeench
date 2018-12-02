package com.victor.ko.jeench.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.victor.ko.jeench.R;
import com.victor.ko.jeench.common.ImageService;
import com.victor.ko.jeench.databinding.ShopListItemBinding;
import com.victor.ko.jeench.service.model.Shop;
import com.victor.ko.jeench.view.callback.ShopClickCallback;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {

    ImageService imageService;

    List<? extends Shop> shopList;

    @Nullable
    private final ShopClickCallback shopClickCallback;

    public ShopAdapter(@Nullable ShopClickCallback shopClickCallback,
                       ImageService imageService) {
        this.shopClickCallback = shopClickCallback;
        this.imageService = imageService;
    }

    public void setShopList(final List<? extends Shop> shopList) {
        if (this.shopList == null) {
            this.shopList = shopList;
            notifyItemRangeInserted(0, shopList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return ShopAdapter.this.shopList.size();
                }

                @Override
                public int getNewListSize() {
                    return shopList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return ShopAdapter.this.shopList.get(oldItemPosition).getShop_id() ==
                            shopList.get(newItemPosition).getShop_id();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Shop shop = shopList.get(newItemPosition);
                    Shop old = shopList.get(oldItemPosition);
                    return shop.getShop_id() == old.getShop_id();
                }
            });
            this.shopList = shopList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ShopListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.shop_list_item,
                        parent, false);

        binding.setCallback(shopClickCallback);

        return new ShopViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ShopViewHolder holder, int position) {
        Shop shop = shopList.get(position);
        holder.binding.setShop(shop);

        ImageView itemImageView = holder.binding.itemImage;
        String itemImageUrl = shop.getItem_image();
        if (!itemImageUrl.isEmpty()) {
            imageService.loadImage(itemImageUrl, itemImageView);
        }

        ImageView logoImageView = holder.binding.logoImage;
        String logoImageUrl = shop.getShop_logo();
        if (!logoImageUrl.isEmpty()) {
            imageService.loadImage(logoImageUrl, logoImageView);
        }

        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return shopList == null ? 0 : shopList.size();
    }

    static class ShopViewHolder extends RecyclerView.ViewHolder {

        final ShopListItemBinding binding;

        public ShopViewHolder(ShopListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
