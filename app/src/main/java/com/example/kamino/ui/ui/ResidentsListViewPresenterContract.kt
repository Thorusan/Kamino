package com.example.kamino.ui.ui

import com.example.kamino.datamodel.KaminoModel
import retrofit2.Response

interface ResidentsListViewPresenterContract {
    interface ViewInterface {
        fun displayResidentData(residentData: Response<KaminoModel.Resident>)
        fun checkInternet(): Boolean
        fun validateError()
        fun onError(throwable: Throwable)
        fun showProgressbar()
        fun hideProgressbar()
    }

    interface PresenterInterface {
        fun getResidentData(residentUrl: String)
        fun onStop()
    }
}