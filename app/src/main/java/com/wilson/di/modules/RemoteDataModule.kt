package com.wilson.di.modules

import com.google.gson.GsonBuilder
import com.wilson.api.KisanHubApiInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RemoteDataModule(val baseUrl : String) {


    @Provides @Singleton
    fun provideApiImple(retrofit: Retrofit) : KisanHubApiInterface
        =retrofit.create(KisanHubApiInterface::class.java)


    @Provides @Singleton
    fun provideRetrofit() : Retrofit
    {
        val gson = GsonBuilder().create()

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

}