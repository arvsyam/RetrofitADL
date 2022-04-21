package com.adl.retrofitadl.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.adl.retrofitadl.CreateNews
import com.adl.retrofitadl.MainActivity
import com.adl.retrofitadl.R
import com.adl.retrofitadl.model.AdlNewsItem
import com.adl.retrofitadl.model.NewsResponse
import com.adl.retrofitadl.model.PostNewsResponse
import com.adl.retrofitadl.services.RetrofitConfig
import okhttp3.internal.notifyAll
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import kotlin.math.log

class MyNewsAdapter(var data: ArrayList<AdlNewsItem?>):RecyclerView.Adapter<MyNewsViewHolder>() {
    lateinit var parent:ViewGroup
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyNewsViewHolder {
        this.parent = parent

        return MyNewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_holder,parent,false))
    }

    override fun onBindViewHolder(holder: MyNewsViewHolder, position: Int) {
        holder.bindData(this@MyNewsAdapter,position)
    }

    override fun getItemCount(): Int {
        Log.d("itemCOunte","${data.size}")
        return data.size
    }

    fun delete(pos:Int){
        val currentNews = data.get(pos)?.id!!
        Log.d("otw delete","${currentNews}")
        RetrofitConfig().getService().deleteNews(currentNews?.toInt()).enqueue(object: Callback<PostNewsResponse>{
            override fun onResponse(
                call: Call<PostNewsResponse>,
                response: Response<PostNewsResponse>
            ) {
                if(response.isSuccessful){
                    Toast.makeText(parent.context,"deleted",Toast.LENGTH_SHORT).show()
                    val intent = Intent(parent.context, MainActivity::class.java)
                    parent.context.startActivity(intent)
                }else{
                    Toast.makeText(parent.context,"failed",Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<PostNewsResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun updateNews(pos:Int){
        val currentNews = data.get(pos)

        val intent = Intent(parent.context, CreateNews::class.java)
        intent.putExtra("data", currentNews)
        parent.context.startActivity(intent)
    }
}