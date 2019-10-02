package com.example.kamino.ui.ui

import com.example.kamino.datamodel.KaminoModel
import com.example.kamino.repositories.DataRepository
import com.example.kamino.repositories.KaminoApiService
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import retrofit2.Response

class MainPresenterImplementation : MainViewPresenterContract.PresenterInterface,
    DataRepository.PlanetDataRepositoryCallback, DataRepository.LikeDataRepositoryCallback {

    private var dataRepository: DataRepository

    var view: MainViewPresenterContract.ViewInterface? = null

    constructor(
        view: MainViewPresenterContract.ViewInterface?,
        dataRepository: DataRepository
    ) {
        this.view = view
        this.dataRepository = dataRepository
    }

    override fun getPlanetData() {
        if (view!!.checkInternet()) {
            dataRepository.callApi_getKaminoPlanet(this)
        } else {
            view!!.hideProgressbar()
            view!!.validateError()
        }
    }

    override fun handleResponsePlanet(response: Response<KaminoModel.KaminoPlanet>) {
        view?.displayPlanetData(response);
    }

    override fun handleErrorPlanet(error: Throwable) {
        view!!.hideProgressbar()
        view!!.onError(error)
        // TODO: implement HttpException
        /* if (error is HttpException) {
            val response = (error as HttpException).response()
            when (response.code()) {
                //Response Code
            }
        } else {
            //Handle Other Errors
        }*/
    }

    override fun likePlanet() {
        if (view!!.checkInternet()) {
            dataRepository.callApi_Like(this)
        } else {
            view!!.hideProgressbar()
            view!!.validateError()
        }
    }

    override fun handleResponseLike(response: Response<KaminoModel.Like>) {
        view?.updateLikes(response);
    }

    override fun handleErrorLike(error: Throwable) {
        view!!.hideProgressbar()
        view!!.onError(error)
    }

    override fun onStop() {
        dataRepository.onCleared()
    }


}