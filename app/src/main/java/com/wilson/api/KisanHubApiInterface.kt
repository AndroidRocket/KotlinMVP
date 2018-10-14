package com.wilson.api


import com.wilson.models.MetOfficeData
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Url

interface KisanHubApiInterface {

    @GET
    fun getMetOfficeData(@Url query: String) : Flowable<List<MetOfficeData>>
}
