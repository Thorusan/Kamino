package com.example.kamino.ui.ui

import com.example.kamino.datamodel.KaminoModel
import retrofit2.Response

interface MainViewPresenterContract {
    interface MainView {
        fun displayPlanetData(planetData: Response<KaminoModel.KaminoPlanet>)
    }

    interface MainPresenter {
        fun loadData()
        fun onStop()
    }
}