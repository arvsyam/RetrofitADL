package com.adl.retrofitadl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.adl.retrofitadl.adapter.MyNewsAdapter
import com.adl.retrofitadl.adapter.NewsAdapter
import com.adl.retrofitadl.model.AdlNewsItem
import com.adl.retrofitadl.model.Data
import com.adl.retrofitadl.model.NewsResponse
import com.adl.retrofitadl.services.RetrofitConfig
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_template.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var myNewsAdapter: MyNewsAdapter
    var listNews = ArrayList<AdlNewsItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadNews()
        initComponent()
    }

    fun initComponent(){
        btn_search.setOnClickListener({

        })
        btn_create.setOnClickListener({
            val intent = Intent(this, CreateNews::class.java)
            startActivity(intent)
        })
    }

    fun loadNews(){
        RetrofitConfig().getService()
            .getAll("-")
            .enqueue(object : Callback<NewsResponse> {
                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    listNews = response.body()?.data?.adlNews as ArrayList<AdlNewsItem>
                    Log.d("Response","${listNews.size}")
                    myNewsAdapter = ArrayList(listNews)?.let { MyNewsAdapter(it) }
                    rcNews.apply{
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = myNewsAdapter
                    }
                }
            })
    }

    override fun onRestart() {
        super.onRestart()
        loadNews()
    }


}