package com.example.kamino.restconnection

import com.example.kamino.datamodel.KaminoModel
import com.example.kamino.restconnection.Constants.Companion.BASE_URL
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface KaminoApiService {
    @GET(BASE_URL)
    fun getKaminoPlanet(
        @Query("name") name: String,
        @Query("rotation_period") rotationPeriod: Int,
        @Query("orbital_period") orbitalPeriod: Int,
        @Query("diameter") diameter: Int,
        @Query("climate") climate: String,
        @Query("gravity") gravity: String,
        @Query("terrain") terrain: String,
        @Query("surface_water") surfaceWater: Int,
        @Query("population") population: Int,
        @Query("residents") residents: Array<KaminoModel.Resident>,
        @Query("created") created: String,
        @Query("edited") edited: String,
        @Query("image_url") imageUrl: String,
        @Query("likes") likes: Int

    ):
            Observable<KaminoModel.KaminoPlanet>
}



