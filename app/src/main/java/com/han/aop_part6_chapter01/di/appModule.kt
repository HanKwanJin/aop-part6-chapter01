package com.han.aop_part6_chapter01.di

import com.han.aop_part6_chapter01.data.entity.LocationLatLngEntity
import com.han.aop_part6_chapter01.data.entity.MapSearchInfoEntity
import com.han.aop_part6_chapter01.data.repository.map.DefaultMapRepository
import com.han.aop_part6_chapter01.data.repository.map.MapRepository
import com.han.aop_part6_chapter01.data.repository.restaurant.DefaultRestaurantRepository
import com.han.aop_part6_chapter01.data.repository.restaurant.RestaurantRepository
import com.han.aop_part6_chapter01.data.repository.user.DefaultUserRepository
import com.han.aop_part6_chapter01.data.repository.user.UserRepository
import com.han.aop_part6_chapter01.screen.main.home.HomeViewModel
import com.han.aop_part6_chapter01.screen.main.home.restaurant.RestaurantCategory
import com.han.aop_part6_chapter01.screen.main.home.restaurant.RestaurantListViewModel
import com.han.aop_part6_chapter01.screen.main.my.MyViewModel
import com.han.aop_part6_chapter01.screen.mylocation.MyLocationViewModel
import com.han.aop_part6_chapter01.util.provider.DefaultResourcesProvider
import com.han.aop_part6_chapter01.util.provider.ResourceProvider
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get(),get()) }
    viewModel { MyViewModel() }
    viewModel {
            (restaurantCategory: RestaurantCategory, locationLatLng: LocationLatLngEntity) ->
        RestaurantListViewModel( restaurantCategory ,locationLatLng, get())
    }
    viewModel {(mapSearchInfoEntity: MapSearchInfoEntity) -> MyLocationViewModel(mapSearchInfoEntity, get(),get()) }

    single<RestaurantRepository> { DefaultRestaurantRepository(get(), get(),get()) }
    single<MapRepository> { DefaultMapRepository(get(),get()) }
    single<UserRepository> { DefaultUserRepository(get(),get()) }

    single { provideGsonConvertFactory() }
    single { buildOkHttpClient() }
    single { provideMapRetrofit(get(), get()) }
    single { provideMapApiService(get()) }

    single { provideDB(androidApplication())}
    single { provideLocationDao(get()) }

    single<ResourceProvider> { DefaultResourcesProvider(androidApplication()) }
    single { Dispatchers.IO }
    single { Dispatchers.Main }

}