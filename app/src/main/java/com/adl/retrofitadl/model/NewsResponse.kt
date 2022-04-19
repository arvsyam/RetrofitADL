package com.adl.retrofitadl.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsResponse(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
) : Parcelable

@Parcelize
data class AdlNewsItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("body")
	val body: String? = null
) : Parcelable

@Parcelize
data class Data(

	@field:SerializedName("adl_news")
	val adlNews: List<AdlNewsItem?>? = null
) : Parcelable
