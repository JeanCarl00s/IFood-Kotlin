package com.jean.ifood

import android.view.ViewGroup
import co.tiagoaguiar.atway.ui.adapter.ATViewHolder
import com.jean.ifood.databinding.BannerItemBinding
import com.squareup.picasso.Picasso

class BannerView(viewGroup: ViewGroup) : ATViewHolder<Banner, BannerItemBinding>(
    BannerItemBinding::inflate,
    viewGroup
) {
    override fun bind(item: Banner) {
        Picasso.get()
            .load(item.bannerUrl)
            .into(binding.imgBanner)
    }
}


