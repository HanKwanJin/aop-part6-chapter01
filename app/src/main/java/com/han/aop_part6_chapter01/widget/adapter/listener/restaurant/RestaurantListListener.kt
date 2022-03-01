package com.han.aop_part6_chapter01.widget.adapter.listener.restaurant

import com.han.aop_part6_chapter01.model.restaurant.RestaurantModel
import com.han.aop_part6_chapter01.widget.adapter.listener.AdapterListener

interface RestaurantListListener: AdapterListener {
    fun onClickItem(model: RestaurantModel)
}