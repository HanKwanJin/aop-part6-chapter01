package com.han.aop_part6_chapter01.screen.main.home

import androidx.annotation.StringRes
import com.han.aop_part6_chapter01.data.entity.LocationLatLngEntity
import com.han.aop_part6_chapter01.data.entity.MapSearchInfoEntity

sealed class HomeState{
    object Uninitialized: HomeState()

    object Loading: HomeState()

    data class Success(
        val mapSearchInfo: MapSearchInfoEntity,
        val isLocationSame : Boolean
    ): HomeState()

    data class Error(
        @StringRes val messageId: Int
    ): HomeState()
}
