package com.han.aop_part6_chapter01.widget.adapter.viewholders


import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.han.aop_part6_chapter01.model.Model
import com.han.aop_part6_chapter01.screen.base.BaseViewModel
import com.han.aop_part6_chapter01.util.provider.ResourceProvider
import com.han.aop_part6_chapter01.widget.adapter.listener.AdapterListener

abstract class ModelViewHolder<M: Model>(
    binding: ViewBinding,
    protected val viewModel: BaseViewModel,
    protected val resourcesProvider: ResourceProvider
): RecyclerView.ViewHolder(binding.root) {
    abstract fun reset()
    open fun bindData(model: M){
        reset()
    }
    abstract fun bindViews(model: M, adapterListener: AdapterListener)
}