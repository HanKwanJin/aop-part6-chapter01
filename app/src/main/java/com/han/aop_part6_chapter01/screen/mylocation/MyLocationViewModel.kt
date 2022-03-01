package com.han.aop_part6_chapter01.screen.mylocation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.han.aop_part6_chapter01.R
import com.han.aop_part6_chapter01.data.entity.LocationLatLngEntity
import com.han.aop_part6_chapter01.data.entity.MapSearchInfoEntity
import com.han.aop_part6_chapter01.data.repository.map.MapRepository
import com.han.aop_part6_chapter01.data.repository.user.UserRepository
import com.han.aop_part6_chapter01.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MyLocationViewModel(
    private val mapSearchInfoEntity: MapSearchInfoEntity,
    private val mapRepository: MapRepository,
    private val userRepository: UserRepository
):BaseViewModel() {
    val myLocationStateLiveData = MutableLiveData<MyLocationState>(MyLocationState.Uninitialized)

    override fun fetchData(): Job = viewModelScope.launch {
        myLocationStateLiveData.value = MyLocationState.Loading
        myLocationStateLiveData.value = MyLocationState.Success(
            mapSearchInfoEntity
        )
    }
    fun changeLocationInfo(
        locationLatLngEntity: LocationLatLngEntity
    ) = viewModelScope.launch {
        val addressInfo = mapRepository.getReverseGeoInformation(locationLatLngEntity)
        addressInfo?.let { info ->
            myLocationStateLiveData.value = MyLocationState.Success(
                mapSearchInfoEntity = info.toSearchInfoEntity(locationLatLngEntity)
            )
        } ?: kotlin.run {
            myLocationStateLiveData.value = MyLocationState.Error(
                R.string.can_not_load_address_info
            )
        }
    }

    fun confirmSelectLocation() = viewModelScope.launch {
        when(val data = myLocationStateLiveData.value){
            is MyLocationState.Success -> {
                userRepository.insertUserLocation(data.mapSearchInfoEntity.locationLatLng)
                myLocationStateLiveData.value = MyLocationState.Confirm(
                    data.mapSearchInfoEntity
                )
            }
        }
    }
}