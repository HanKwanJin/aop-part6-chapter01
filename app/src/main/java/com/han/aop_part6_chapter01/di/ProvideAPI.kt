package com.han.aop_part6_chapter01.di

import com.han.aop_part6_chapter01.data.network.MapApiService
import com.han.aop_part6_chapter01.data.url.TmapUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


fun provideMapApiService(retrofit: Retrofit): MapApiService{
    return retrofit.create(MapApiService::class.java)
}
fun provideMapRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory
): Retrofit{
    return Retrofit.Builder()
        .baseUrl(TmapUrl.TMAP_URL)
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()
}

fun provideGsonConvertFactory(): GsonConverterFactory{
    return GsonConverterFactory.create()
}
fun buildOkHttpClient(): OkHttpClient{
    val interceptor = HttpLoggingInterceptor()
    if(BuildConfig.DEBUG){
        interceptor.level = HttpLoggingInterceptor.Level.NONE
    }
    return OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}