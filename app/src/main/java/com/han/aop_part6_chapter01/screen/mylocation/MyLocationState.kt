package com.han.aop_part6_chapter01.screen.mylocation

import androidx.annotation.StringRes
import com.han.aop_part6_chapter01.data.entity.MapSearchInfoEntity

sealed class MyLocationState{
    object Uninitialized: MyLocationState()
    object Loading: MyLocationState()
    data class Success(
        val mapSearchInfoEntity: MapSearchInfoEntity
    ): MyLocationState()

    data class Confirm(
        val mapSearchInfoEntity: MapSearchInfoEntity
    ): MyLocationState()

    data class Error(
        @StringRes val messageId: Int
    ): MyLocationState()
}
