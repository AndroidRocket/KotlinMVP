package com.wilson

import android.support.v4.app.FragmentActivity

interface BaseView<T>
{
    fun getActivityView(): FragmentActivity?

    fun onNetworkError()
}
