package com.wilson.presenters

import com.google.gson.Gson
import com.wilson.models.MetOfficeData
import com.wilson.repositories.MetOfficeDataRepository
import com.wilson.utils.Utils
import javax.inject.Inject
import com.google.gson.reflect.TypeToken



class MetOfficeDataPresenter(view: MetOfficeDataContract.View) :  MetOfficeDataContract.Presenter
{

    val gson = Gson()


    var view: MetOfficeDataContract.View = view

    @Inject
    lateinit var repository: MetOfficeDataRepository



    override fun getMetOfficeData(strDataX: String): List<MetOfficeData>? {
        var qList: List<MetOfficeData>

        if (!strDataX.equals("", ignoreCase = true)) {
            val type = object : TypeToken<List<MetOfficeData>>() {
            }.type
            qList = gson.fromJson(strDataX, type)
            return if (qList.isNotEmpty()) qList else null
        }
        return null
    }

    override fun checkInternet(): Boolean {
        return Utils.isNetworkConnected(view.getActivityView()!!)
    }



    fun getMetOfficeData(matrix: String, location: String) {

        if(checkInternet()){
//            dialog.show()
            repository.getList(
                    {
                        view.setMetOfficeData(it!!)
//                        dialog.dismiss()
                    },
                    {
                    },matrix,location)
        }
        else{
            repository.checkOfflineData({
                view.setMetOfficeData(it!!)
                view.onNetworkError()
//                dialog.dismiss()
            },{})

        }

    }
    override fun initProgressDialog() {

    }
}