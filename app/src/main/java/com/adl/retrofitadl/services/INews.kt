package com.adl.retrofitadl.services

import com.adl.retrofitadl.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface INews {
    @Headers("X-Api-Key:FC35B899DC137F0CB6B56035FA37068A")
    @GET("api/adl_news/all")
    fun getAll(): Call<NewsResponse>

}