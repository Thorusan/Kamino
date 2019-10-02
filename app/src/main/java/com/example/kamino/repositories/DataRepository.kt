package com.example.kamino.repositories

import com.example.kamino.common.Constants
import com.example.kamino.datamodel.KaminoModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class DataRepository {
    val kaminoApiService: KaminoApiService
    private val compositeDisposable = CompositeDisposable()

    constructor(
        kaminoApiService: KaminoApiService
    ) {
        this.kaminoApiService = kaminoApiService
    }

    fun callApi_getKaminoPlanet(dataRepositoryCallback: PlanetDataRepositoryCallback) {
        compositeDisposable.add(kaminoApiService.getKaminoPlanet()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val responseCode = response.code()
                when (responseCode) {
                    200, 201, 202 -> {
                        dataRepositoryCallback.handleResponsePlanet(response);
                    }
                    401 -> {
                    }
                    402 -> {
                    }
                    500 -> {
                    }
                    501 -> {
                    }
                }
            },
                { error ->
                    dataRepositoryCallback.handleErrorPlanet(error)
                }
            ))
    }

    fun callApi_Like(dataRepositoryCallback: LikeDataRepositoryCallback) {
        compositeDisposable.add(kaminoApiService.likeKaminoPlanet(Constants.PLANET_ID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->

                val responseCode = response.code()
                when (responseCode) {
                    200, 201, 202 -> {
                        dataRepositoryCallback.handleResponseLike(response);
                    }
                    401 -> {
                    }
                    402 -> {
                    }
                    500 -> {
                    }
                    501 -> {
                    }
                }
            },
                { error ->
                    dataRepositoryCallback.handleErrorLike(error)
                }
            ))
    }

    fun onCleared() {
        if(!compositeDisposable.isDisposed){
            compositeDisposable.dispose()
        }
    }

    interface PlanetDataRepositoryCallback {
        fun handleResponsePlanet(response: Response<KaminoModel.KaminoPlanet>)
        fun handleErrorPlanet(error: Throwable)
    }

    interface LikeDataRepositoryCallback {
        fun handleResponseLike(response: Response<KaminoModel.Like>)
        fun handleErrorLike(error: Throwable)
    }


}