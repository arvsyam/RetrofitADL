package com.adl.retrofitadl.services

import com.adl.retrofitadl.model.OMDBResponse
import com.adl.retrofitadl.model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IMovieDetail {
    @GET("?t=titanic&apikey=e28745b5")
    fun getDetailMovie(): Call<OMDBResponse>

    @GET("?apikey=80641bfb")
    fun searchMovie(@Query("s")query:String): Call<SearchResponse>

}