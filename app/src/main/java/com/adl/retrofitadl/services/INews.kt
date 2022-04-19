package com.adl.retrofitadl.services

import com.adl.retrofitadl.NewsModel
import com.adl.retrofitadl.model.AdlNewsItem
import com.adl.retrofitadl.model.NewsResponse
import retrofit2.Call
import retrofit2.http.*

interface INews {
    @Headers("X-Api-Key:FC35B899DC137F0CB6B56035FA37068A")
    @GET("api/adl_news/all")
    fun getAll(): Call<NewsResponse>

    @Headers("X-Api-Key:FC35B899DC137F0CB6B56035FA37068A")
    @POST("api/adl_news/add/")
    fun saveNews(@Body newsItem: AdlNewsItem):Call<AdlNewsItem>



}