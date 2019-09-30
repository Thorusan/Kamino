package com.example.kamino.ui.ui

import com.example.kamino.common.Constants.Companion.RESIDENT_BOBA_FETT
import com.example.kamino.common.Constants.Companion.RESIDENT_LAMA_SU
import com.example.kamino.common.Constants.Companion.RESIDENT_TAUN_WE
import com.example.kamino.datamodel.KaminoModel
import com.example.kamino.restconnection.KaminoApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class ResidentsListPresenterImplementation : ResidentsListViewPresenterContract.PresenterInterface {
    val kaminoApiservice by lazy {
        KaminoApiService.create()
    }

    var view: ResidentsListViewPresenterContract.ViewInterface? = null

    @NonNull
    var disposable: Disposable? = null

    constructor(view: ResidentsListViewPresenterContract.ViewInterface?) {
        this.view = view
    }

    override fun getResidentData(residentUrl: String) {
        lateinit var observable: Observable<Response<KaminoModel.Resident>>
        if (residentUrl.endsWith(RESIDENT_BOBA_FETT)) {
            observable = kaminoApiservice.getResidentBobaFett(residentUrl)
        } else if (residentUrl.endsWith(RESIDENT_LAMA_SU)) {
            observable = kaminoApiservice.getResidentLamaSu(residentUrl)
        } else if (residentUrl.endsWith(RESIDENT_TAUN_WE)) {
            observable = kaminoApiservice.getResidentTaunWe(residentUrl)
        }

        disposable = observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                //view!!.hideProgressbar()
                val responseCode = response.code()
                when (responseCode) {
                    200, 201, 202 -> {
                        view?.displayResidentData(response);
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