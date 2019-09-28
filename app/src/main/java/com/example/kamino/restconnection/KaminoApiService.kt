package com.example.kamino.restconnection

import com.example.kamino.common.Constants.Companion.BASE_URL
import com.example.kamino.datamodel.KaminoModel
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface KaminoApiService {
    @GET(BASE_URL+"/planets/10")
    fun getKaminoPlanet(): Observable<KaminoModel.KaminoPlanet>


    companion object {
        fun create(): KaminoApiService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(KaminoApiService::class.java)
        }
    }
}



