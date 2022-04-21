package com.adl.retrofitadl.services

import com.adl.retrofitadl.model.AdlNewsItem
import com.adl.retrofitadl.model.NewsResponse
import com.adl.retrofitadl.model.PostNewsResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface INews {
    @Headers("X-Api-Key:FC35B899DC137F0CB6B56035FA37068A")
    @GET("api/adl_news/all?")
    fun getAll(@Query("limit") query:String): Call<NewsResponse>

    @Multipart
    @Headers("X-Api-Key:FC35B899DC137F0CB6B56035FA37068A")
    @POST("api/adl_news/add")
    fun addNews(@Part("title") title: RequestBody,
                @Part("body") body: RequestBody,
                @Part file: MultipartBody.Part):Call<PostNewsResponse>

    @FormUrlEncoded
    @Headers("X-Api-Key:FC35B899DC137F0CB6B56035FA37068A")
    @POST("api/adl_news/delete")
    fun deleteNews(@Field("id") id:Int):Call<PostNewsResponse>

    @Multipart
    @Headers("X-Api-Key:FC35B899DC137F0CB6B56035FA37068A")
    @POST("api/adl_news/update")
    fun updateNews(@Part("id") id: RequestBody,
                @Part("title") title: RequestBody,
                @Part("body") body: RequestBody,
                @Part file: MultipartBody.Part):Call<PostNewsResponse>

}