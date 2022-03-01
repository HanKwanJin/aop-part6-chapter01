package com.han.aop_part6_chapter01.util.mapper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.han.aop_part6_chapter01.databinding.ViewholderEmptyBinding
import com.han.aop_part6_chapter01.databinding.ViewholderRestaurantBinding
import com.han.aop_part6_chapter01.model.CellType
import com.han.aop_part6_chapter01.model.Model
import com.han.aop_part6_chapter01.screen.base.BaseViewModel
import com.han.aop_part6_chapter01.util.provider.ResourceProvider
import com.han.aop_part6_chapter01.widget.adapter.viewholders.EmptyViewHolder
import com.han.aop_part6_chapter01.widget.adapter.viewholders.ModelViewHolder
import com.han.aop_part6_chapter01.widget.adapter.viewholders.restaurant.RestaurantViewHolder

object ModelViewHolderMapper {
    @Suppress("UNCHECKED_CAST")
    fun <M: Model> map(
        parent: ViewGroup,
        type: CellType,
        viewModel: BaseViewModel,
        resourcesProvider: ResourceProvider
    ): ModelViewHolder<M>{
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder = when(type){
            CellType.EMPTY_CELL -> EmptyViewHolder(
                ViewholderEmptyBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
            CellType.RESTAURANT_CELL -> RestaurantViewHolder(
                ViewholderRestaurantBinding.inflate(inflater, parent, false),
                viewModel,
                resourcesProvider
            )
        }
        return viewHolder as ModelViewHolder<M>
    }
}