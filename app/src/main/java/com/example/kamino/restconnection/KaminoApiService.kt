package com.example.kamino.restconnection

import com.example.kamino.common.Constants.Companion.BASE_URL
import com.example.kamino.common.Constants.Companion.RESIDENT_BOBA_FETT
import com.example.kamino.common.Constants.Companion.RESIDENT_LAMA_SU
import com.example.kamino.common.Constants.Companion.RESIDENT_TAUN_WE
import com.example.kamino.datamodel.KaminoModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url


interface KaminoApiService {
    @GET(BASE_URL + "/planets/10")
    fun getKaminoPlanet(): Observable<Response<KaminoModel.KaminoPlanet>>

    @GET
    fun getResidentBobaFett(@Url url: String): Observable<Response<KaminoModel.Resident>>

    @GET
    fun getResidentLamaSu(@Url url: String): Observable<Response<KaminoModel.Resident>>

    @GET
    fun getResidentTaunWe(@Url url: String): Observable<Response<KaminoModel.Resident>>

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



