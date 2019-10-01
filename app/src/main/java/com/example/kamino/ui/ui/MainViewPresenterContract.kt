package com.example.kamino.ui.ui

import com.example.kamino.datamodel.KaminoModel
import retrofit2.Response

interface MainViewPresenterContract {
    interface ViewInterface {
        fun displayPlanetData(planetData: Response<KaminoModel.KaminoPlanet>)
        fun updateLikes(like: Response<KaminoModel.Like>)
        fun checkInternet(): Boolean
        fun onError(throwable: Throwable)
        fun validateError()
        fun showProgressbar()
        fun hideProgressbar()
    }

    interface PresenterInterface {
        fun getPlanetData()
        fun onStop()
        fun likePlanet()
    }
}