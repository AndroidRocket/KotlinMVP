package com.wilson.di.compenents

import com.wilson.MyApp
import com.wilson.di.modules.DatabaseModule
import com.wilson.di.modules.PostRepoModule
import com.wilson.di.modules.RemoteDataModule
import com.wilson.presenters.MetOfficeDataPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(DatabaseModule::class, RemoteDataModule::class,PostRepoModule::class))
interface AppComponent {

    fun inject(mMetOfficeDataPresenter: MetOfficeDataPresenter)

    fun inject(myApp: MyApp)
}