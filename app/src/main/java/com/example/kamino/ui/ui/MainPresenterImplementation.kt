package com.example.kamino.ui.ui

import com.example.kamino.common.Constants.Companion.PLANET_ID
import com.example.kamino.network.KaminoApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class MainPresenterImplementation : MainViewPresenterContract.PresenterInterface {

    val kaminoApiservice by lazy {
        KaminoApiService.create()
    }

    var view: MainViewPresenterContract.ViewInterface? = null

    @NonNull
    var disposable: Disposable? = null
    var disposableLikes: Disposable? = null

    constructor(view: MainViewPresenterContract.ViewInterface?) {
        this.view = view
    }

    override fun getPlanetData() {
        if (view!!.checkInternet()) {
            disposable = kaminoApiservice.getKaminoPlanet()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    view!!.hideProgressbar()
                    val responseCode = response.code()
                    when (responseCode) {
                        200, 201, 202 -> {
                            view?.displayPlanetData(response);
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
                        view!!.hideProgressbar()
                        if (error is HttpException) {
                            val response = (error as HttpException).response()
                            when (response.code()) {
                                //Response Code
                            }
                        } else {
                            //Handle Other Errors
                        }
                        view!!.onError(error)
                    }
                )
        } else {
            view!!.hideProgressbar()
            view!!.validateError()
        }
    }

    override fun likePlanet() {
        if (view!!.checkInternet()) {
            disposableLikes = kaminoApiservice.likeKaminoPlanet(PLANET_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    view!!.hideProgressbar()
                    val responseCode = response.code()
                    when (responseCode) {
                        200, 201, 202 -> {
                            view?.updateLikes(response);
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
                        if (error is HttpException) {
                            val response = (error as HttpException).response()
                            when (response.code()) {
                                //Response Code
                            }
                        } else {
                            //Handle Other Errors
                        }
                        view!!.onError(error)
                    }
                )
        } else {
            view!!.hideProgressbar()
            view!!.validateError()
        }
    }


    override fun onStop() {
        if (disposable != null) {
            disposable!!.dispose()
        }
    }


}