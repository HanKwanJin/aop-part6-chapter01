package com.han.aop_part6_chapter01.widget.adapter.viewholders

import com.han.aop_part6_chapter01.databinding.ViewholderEmptyBinding
import com.han.aop_part6_chapter01.model.Model
import com.han.aop_part6_chapter01.screen.base.BaseViewModel
import com.han.aop_part6_chapter01.util.provider.ResourceProvider
import com.han.aop_part6_chapter01.widget.adapter.listener.AdapterListener

class EmptyViewHolder(
    private val binding: ViewholderEmptyBinding,
    viewModel: BaseViewModel,
    resourcesProvider: ResourceProvider
): ModelViewHolder<Model>(binding, viewModel, resourcesProvider) {
    override fun reset() = Unit

    override fun bindViews(model: Model, adapterListener: AdapterListener) = Unit
}