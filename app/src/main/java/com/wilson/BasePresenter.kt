package com.wilson


interface BasePresenter<T> {
    fun checkInternet():Boolean
    fun initProgressDialog()
}
