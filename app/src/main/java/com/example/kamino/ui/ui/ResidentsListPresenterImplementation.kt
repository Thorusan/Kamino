package com.example.kamino.ui.ui

import com.example.kamino.datamodel.KaminoModel
import com.example.kamino.repositories.DataRepository
import retrofit2.Response

class ResidentsListPresenterImplementation : ResidentsListViewPresenterContract.PresenterInterface,
    DataRepository.ResidentDataRepositoryCallback {

    private var dataRepository: DataRepository
    var view: ResidentsListViewPresenterContract.ViewInterface? = null


    constructor(
        view: ResidentsListViewPresenterContract.ViewInterface?,
        dataRepository: DataRepository
    ) {
        this.view = view
        this.dataRepository = dataRepository
    }

    override fun getResidentData(residentUrl: String) {
        view!!.showProgressbar()

        if (view!!.checkInternet()) {
            dataRepository?.callApi_getResidentData(this, residentUrl);
        } else {
            view!!.hideProgressbar()
            view!!.validateError()
        }
    }

    override fun handleResponse(response: Response<KaminoModel.Resident>) {
        view?.displayResidentData(response);
        view!!.hideProgressbar()
    }

    override fun handleError(error: Throwable) {
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

    override fun onStop() {
        dataRepository.onCleared()
    }


}