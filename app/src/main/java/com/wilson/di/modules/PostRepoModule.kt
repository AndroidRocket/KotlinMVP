package com.wilson.di.modules

import com.wilson.repositories.MetOfficeDataRepository
import com.wilson.repositories.MetOfficeDataRepositoryImpl
import com.wilson.api.KisanHubApiInterface
import com.wilson.models.dao.MetOfficeDataDao
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class PostRepoModule {

    @Provides
    @Singleton
    fun providePostRepository(localSource: MetOfficeDataDao, remoteSource: KisanHubApiInterface): MetOfficeDataRepository
            = MetOfficeDataRepositoryImpl(localSource, remoteSource)
}