package com.wilson.repositories

import android.util.Log
import com.wilson.api.KisanHubApiInterface


import com.wilson.models.MetOfficeData

import com.wilson.models.dao.MetOfficeDataDao
import io.reactivex.Completable
import io.reactivex.CompletableObserver

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MetOfficeDataRepositoryImpl(val localSource: MetOfficeDataDao, val remoteSource: KisanHubApiInterface) : MetOfficeDataRepository {
    override fun checkOfflineData(successHandler: (List<MetOfficeData>?) -> Unit, failureHandler: (Throwable?) -> Unit) {
        localSource.getMetOfficeData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { users -> successHandler(users) }
    }


    override fun getList(successHandler: (List<MetOfficeData>?) -> Unit, failureHandler: (Throwable?) -> Unit, matrix : String, location : String) {


        val observable: Flowable<List<MetOfficeData>> = remoteSource.getMetOfficeData("interview-question-data/metoffice/$matrix-$location.json")

        observable.observeOn(mainThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->

                            //FIXME replace this pattern to flatmap
                            Completable.fromAction {
                                //FIXME there is no paging and unique id available in API as of now.
                                localSource.deleteAll()
                                localSource.insertAll(result as ArrayList<MetOfficeData>)
                            }.observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io()).subscribe(object : CompletableObserver {
                                        override fun onSubscribe(d: Disposable) {}

                                        override fun onComplete() {

                                            localSource.getMetOfficeData().subscribeOn(Schedulers.io())
                                                    .observeOn(AndroidSchedulers.mainThread())
                                                    .subscribe { users -> successHandler(users) }
                                        }

                                        override fun onError(e: Throwable) {
                                            Log.e("onError", e.localizedMessage)
                                        }
                                    })

                        },
                        { error -> failureHandler(error) }
                )
    }


}