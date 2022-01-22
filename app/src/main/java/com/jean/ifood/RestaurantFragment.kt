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

    private val categoryAdapter = ATAdapter( {
        CategoryView(it)
    })
    private val bannerAdapter = ATAdapter( {
        BannerView(it)
    })
    private val shopAdapter = ATAdapter( {
        ShopView(it)
    })
    private val moreShopAdapter = ATAdapter( {
        MoreShopView(it)
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

        shopAdapter.items = arrayListOf(
            Shop(1,"https://static-images.ifood.com.br/image/upload/t_thumbnail/logosgde/Logo%20McDonald_MCDON_DRIV15.jpg", "MC Donalds"),
            Shop(2,"https://static-images.ifood.com.br/image/upload/t_thumbnail/logosgde/201910292243_94aaf166-84cc-4ebf-a35d-d223be34d01f.png", "Coco Bambu"),
            Shop(3,"https://static-images.ifood.com.br/image/upload/t_thumbnail/logosgde/d4a3984f-2b73-4f46-99df-1d6bc79ff293/202001031317_CXpO_i.png", "China Box"),
            Shop(5,"https://static-images.ifood.com.br/image/upload/t_thumbnail/logosgde/201801231937__HABIB_VERDE.jpg", "Habibs"),
            Shop(6,"https://static-images.ifood.com.br/image/upload/t_thumbnail/logosgde/201906182008_2b157a73-7564-4733-94c1-8d0376e7bb39.png", "Outback"),
            Shop(7,"https://static-images.ifood.com.br/image/upload/t_thumbnail/logosgde/afd87cab-7dee-4ed6-88bf-168ceef5bc87/202104232246_vgkL_i.png", "Ohana Acai"),
        )

        moreShopAdapter.items = arrayListOf(
            MoreShop(1, "https://static-images.ifood.com.br/image/upload/t_high/logosgde/9a16bafe-ac6f-498b-8dd5-fd77474e8c5c/202107041151_StAn_i.jpg", "Dona Gula",5.0, "Doces", 5.0, "60-70", 15.00),
            MoreShop(2, "https://static-images.ifood.com.br/image/upload/t_high/logosgde/9d352120-8c3b-4fbb-9152-9b5fe8201699/202108111439_xQxA_i.png", "Dela Porto",4.4, "Acai", 3.0, "60-70", 20.00),
            MoreShop(3, "https://static-images.ifood.com.br/image/upload/t_high/logosgde/2a023fb0-fedc-4a0a-8831-8f13fac92700/201907242027_5NST_i.png", "Formiga Amiga",4.0, "Doces", 1.0, "60-70", 40.00),
            MoreShop(4, "https://static-images.ifood.com.br/image/upload/t_high/logosgde/5fce6ed6-4626-483b-98ea-5af2e299acae/201906252035_hkE8_i.jpg", "Tentacao",4.3, "Doces", 8.0, "60-70", 25.00),
            MoreShop(5, "https://static-images.ifood.com.br/image/upload/t_high/logosgde/afd87cab-7dee-4ed6-88bf-168ceef5bc87/202104232246_vgkL_i.png", "Ohana",4.0, "Acai", 1.5, "60-70", 20.00),
            MoreShop(6, "https://static-images.ifood.com.br/image/upload/t_high/logosgde/68c4dc21-7496-4abe-bc29-bb93636f1d85/202009111454_PNVn_i.png", "Thoscana",3.5, "Grill", 11.5, "60-70", 50.00),
            )

        binding = FragmentRestaurantBinding.bind(view)

        binding?.let {
            it.rvCategory.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            it.rvCategory.adapter = categoryAdapter

            it.rvBanners.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            it.rvBanners.adapter = bannerAdapter

            it.rvShops.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            it.rvShops.adapter = shopAdapter

            it.rvMoreShops.layoutManager = LinearLayoutManager(requireContext())
            it.rvMoreShops.adapter = moreShopAdapter

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

