package com.wilson

import android.app.Application
import com.wilson.di.compenents.AppComponent
import com.wilson.di.compenents.DaggerAppComponent
import com.wilson.di.modules.DatabaseModule
import com.wilson.di.modules.PostRepoModule
import com.wilson.di.modules.RemoteDataModule


class MyApp : Application()
{

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        setupGraph()
    }

    private fun setupGraph() {
        component =
                DaggerAppComponent.builder()
                .databaseModule(DatabaseModule(applicationContext))
                .remoteDataModule(RemoteDataModule(BuildConfig.BASE_URL))
                .postRepoModule(PostRepoModule())
                .build()
    }
}