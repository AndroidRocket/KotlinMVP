package com.wilson.views.activity

import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.wilson.R
import com.wilson.controller.MetOfficeDataController
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.view.*


open class HomeActivity : AppCompatActivity() {

    private var router: Router? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        toolbar.title=""
        router = Conductor
                .attachRouter(this, container, savedInstanceState)
        toolbar.navigationIcon = null
        if (!router!!.hasRootController()) {
            var mMetOfficeDataController  = MetOfficeDataController()
            mMetOfficeDataController.retainViewMode = Controller.RetainViewMode.RETAIN_DETACH
            router!!.setRoot(RouterTransaction
                    .with(mMetOfficeDataController))
        }

    }

    fun setOfflineView(visibility : Int){
        toolbar.txtTitle.visibility = visibility
        toolbar.txtTitle.setBackgroundColor(ContextCompat.getColor(this as Context,android.R.color.holo_red_dark))
    }

}
