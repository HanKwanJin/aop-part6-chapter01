package com.han.aop_part6_chapter01.screen.main.my

import com.han.aop_part6_chapter01.databinding.FragmentHomeBinding
import com.han.aop_part6_chapter01.databinding.FragmentMyBinding
import com.han.aop_part6_chapter01.screen.base.BaseFragment
import com.han.aop_part6_chapter01.screen.main.home.HomeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyFragment:BaseFragment<MyViewModel, FragmentMyBinding>() {
    override val viewModel by viewModel<MyViewModel>()
    override fun getViewBinding(): FragmentMyBinding = FragmentMyBinding.inflate(layoutInflater)
    override fun observeData() {

    }

    companion object{
        fun newInstance() = MyFragment()
        const val TAG = "MyFragment"
    }
}