package com.wilson.repositories


import com.wilson.models.MetOfficeData

interface MetOfficeDataRepository {


   fun getList(successHandler: (List<MetOfficeData>?)->Unit, failureHandler : (Throwable?)->Unit, matrix : String, location : String)

   fun checkOfflineData(successHandler: (List<MetOfficeData>?)->Unit, failureHandler : (Throwable?)->Unit)

}