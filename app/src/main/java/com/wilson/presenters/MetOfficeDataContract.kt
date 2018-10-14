package com.wilson.presenters

import com.wilson.BasePresenter
import com.wilson.BaseView
import com.wilson.models.MetOfficeData


interface MetOfficeDataContract {

    interface View : BaseView<Presenter> {
        fun setMetOfficeData(tasks: List<MetOfficeData>)
        fun setOfflineScreen()
        fun setOnlineScreen()

    }

    interface Presenter : BasePresenter<View> {
        fun getMetOfficeData(strDataX: String): List<MetOfficeData>?
    }
}
