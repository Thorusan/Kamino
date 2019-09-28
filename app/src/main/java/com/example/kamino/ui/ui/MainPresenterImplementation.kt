package com.example.kamino.ui.ui

import com.example.kamino.restconnection.KaminoApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainPresenterImplementation : MainViewPresenterContract.MainPresenter {
    val kaminoApiservice by lazy {
        KaminoApiService.create()
    }

    var mainView: MainViewPresenterContract.MainView? = null

    @NonNull
    var disposable: Disposable? = null

    constructor(mainView: MainViewPresenterContract.MainView?) {
        this.mainView = mainView
    }

    override fun loadData() {
        disposable = kaminoApiservice.getKaminoPlanet()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                //mainView!!.hideProgressbar()
                val responseCode = response.code()
                when (responseCode) {
                    200, 201, 202 -> {
                        mainView?.displayPlanetData(response);
                    }
                    401 -> { }
                    402 -> { }
                    500 -> { }
                    501 -> { }
                }
            },
                { error ->
                    System.out.println(error)
                }
            )
    }

    override fun onStop() {
        if (disposable != null) {
            disposable!!.dispose()
        }
    }


}