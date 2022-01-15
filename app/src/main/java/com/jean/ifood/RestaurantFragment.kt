package com.jean.ifood

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.atway.ui.adapter.ATAdapter
import com.jean.ifood.databinding.FragmentRestaurantBinding

class RestaurantFragment : Fragment(R.layout.fragment_restaurant) {

    private var binding: FragmentRestaurantBinding? = null

    private var categoryAdapter = ATAdapter( {
        CategoryView(it)
    })
    private var bannerAdapter = ATAdapter( {
        BannerView(it)
    })

    private var filters = arrayOf(
        FilterItem(1,"Ordenar", closeIcon = R.drawable.ic_baseline_keyboard_arrow_down_24),
        FilterItem(2,"Pra retirar", icon =R.drawable.ic_baseline_directions_walk_24),
        FilterItem(3,"Entrega gratis"),
        FilterItem(4,"Vale-refeicao", closeIcon = R.drawable.ic_baseline_keyboard_arrow_down_24),
        FilterItem(5,"Distancia", closeIcon = R.drawable.ic_baseline_keyboard_arrow_down_24),
        FilterItem(6,"Entrega parceria"),
        FilterItem(7,"Super restaurate"),
        FilterItem(8,"Filtros", closeIcon = R.drawable.ic_baseline_filter_list_24)
    )



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryAdapter.items = arrayListOf(
            Category(1, "https://www.ifood.com.br/static/images/categories/market.png", "mercado", 0xFFB6D048),
            Category(2, "https://www.ifood.com.br/static/images/categories/restaurant.png", "Restaurante", 0xFFE91D2D),
            Category(3, "https://www.ifood.com.br/static/images/categories/drinks.png", "Bebidas", 0xFFF6D553),
            Category(4, "https://static-images.ifood.com.br/image/upload/f_auto/webapp/landingV2/express.png", "Express", 0xFFF0000),
            Category(5, "https://parceiros.ifood.com.br/static/media/salad.9db040c0.png", "Saudavel", 0xFFE91D2D),
            Category(6, "https://www.ifood.com.br/static/images/categories/drinks.png", "Salgados", 0xFF8C60C5),
        )

        bannerAdapter.items = arrayListOf(
            Banner(1, "https://static-images.ifood.com.br/image/upload/t_high,q_100/webapp/landing/landing-banner-1.png"),
            Banner(2,"https://static-images.ifood.com.br/image/upload/t_high,q_100/webapp/landing/landing-banner-2.png"),
            Banner(3, "https://static-images.ifood.com.br/image/upload/t_high,q_100/webapp/landing/landing-banner-3.png"),
        )

        binding = FragmentRestaurantBinding.bind(view)

        binding?.let {
            it.rvCategory.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            it.rvCategory.adapter = categoryAdapter

            it.rvBanners.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            it.rvBanners.adapter = bannerAdapter
            it.rvBanners.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE){
                        notifyPositionChanged(recyclerView)
                    }
                }
            })

            addDots(it.dots, bannerAdapter.items.size, 0)

            filters.forEach { filter ->
                it.chipGroupFilter.addView(filter.toChip(requireContext()))
            }
        }
    }
    private fun addDots(container: LinearLayout, size: Int, position: Int) {
        container.removeAllViews()

        Array(size) {
            val textView = TextView(context).apply {
                text = getString(R.string.dotted)
                textSize = 20f
                setTextColor(
                    if (position == it) ContextCompat.getColor(context, android.R.color.black)
                    else ContextCompat.getColor(context, android.R.color.darker_gray)
                )
            }
            container.addView(textView)
        }
    }
    private var position:Int? = RecyclerView.NO_POSITION
    private var snapHelper = LinearSnapHelper()

    private fun notifyPositionChanged(recyclerView: RecyclerView) {
        val layoutManager = recyclerView.layoutManager
        val view = snapHelper.findSnapView(layoutManager)
        val position = if (view == null) RecyclerView.NO_POSITION else layoutManager?.getPosition(view)

        val positionChanged = this.position != position
        if (positionChanged) {
            addDots(binding!!.dots, bannerAdapter.items.size, position ?: 0)
        }
        this.position = position
    }
}

