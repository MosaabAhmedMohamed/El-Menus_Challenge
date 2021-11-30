package com.example.el_menus_challenge.di.appmodules

import com.example.el_menus_challenge.util.data.APIConst
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule {

    @Singleton
    @Provides
    internal fun provideHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(APIConst.TIME_OUT, TimeUnit.SECONDS)
        httpClient.readTimeout(APIConst.TIME_OUT, TimeUnit.SECONDS)
        httpClient.writeTimeout(APIConst.TIME_OUT, TimeUnit.SECONDS)
        httpClient.retryOnConnectionFailure(true)

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor(logging)

        return httpClient.build()
    }


    @Singleton
    @Provides
    fun provideRetrofitBuilder(httpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(APIConst.BASE_URL)
            .client(httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())

    }

}