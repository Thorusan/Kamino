package com.example.kamino.ui.ui

import com.example.kamino.datamodel.KaminoModel
import retrofit2.Response

interface MainViewPresenterContract {
    interface ViewInterface {
        fun displayPlanetData(planetData: Response<KaminoModel.KaminoPlanet>)
        fun updateLikes(like: Response<KaminoModel.Like>)
    }

    interface PresenterInterface {
        fun getPlanetData()
        fun onStop()
        fun likePlanet()
    }
}