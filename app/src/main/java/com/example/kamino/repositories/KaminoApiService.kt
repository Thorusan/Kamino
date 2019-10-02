package com.example.kamino.repositories

import com.example.kamino.common.Constants.Companion.BASE_URL
import com.example.kamino.datamodel.KaminoModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface KaminoApiService {
    @GET(BASE_URL + "/planets/10")
    fun getKaminoPlanet(): Observable<Response<KaminoModel.KaminoPlanet>>

    @GET
    fun getResidentBobaFett(@Url url: String): Observable<Response<KaminoModel.Resident>>

    @GET
    fun getResidentLamaSu(@Url url: String): Observable<Response<KaminoModel.Resident>>

    @GET
    fun getResidentTaunWe(@Url url: String): Observable<Response<KaminoModel.Resident>>

    @FormUrlEncoded
    @POST(BASE_URL + "/planets/10/like")
    fun likeKaminoPlanet(@Field("planet_id") planetId: Int)
            : Observable<Response<KaminoModel.Like>>

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



