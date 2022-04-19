package com.adl.retrofitadl

import android.app.PendingIntent.getService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.adl.retrofitadl.adapter.MovieAdapter
import com.adl.retrofitadl.model.OMDBResponse
import com.adl.retrofitadl.model.SearchItem
import com.adl.retrofitadl.model.SearchResponse
import com.adl.retrofitadl.services.RetrofitConfig
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.movie_holder.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    var lstMovie = ArrayList<SearchItem?>()
    lateinit var movieAdapter : MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_search.setOnClickListener ({
            RetrofitConfig().getService()
                .searchMovie(et_search.text.toString())
                .enqueue(object : retrofit2.Callback<SearchResponse>{
                    override fun onResponse(
                        call: Call<SearchResponse>,
                        response: Response<SearchResponse>
                    ) {


                        lstMovie = ArrayList(response.body()?.search!!)
                        Log.d("list size","${lstMovie.size}")


                    }

                    override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                        Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                    }

                })
        })
    }


}