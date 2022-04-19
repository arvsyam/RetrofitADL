package com.adl.retrofitadl

import android.app.PendingIntent.getService
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.adl.retrofitadl.adapter.MovieAdapter
import com.adl.retrofitadl.adapter.NewsAdapter
import com.adl.retrofitadl.model.*
import com.adl.retrofitadl.services.RetrofitConfig
import com.panaceasoft.pskotlinmaterial.adapter.feature.list.education.FeatureListEducationCategoryList1Adapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_template.*
import kotlinx.android.synthetic.main.movie_holder.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var listDataHeader: MutableList<String>
    private lateinit var listDataChild: HashMap<String, List<AdlNewsItem>>
    private lateinit var newsAdapter: NewsAdapter


    var lstMovie = ArrayList<SearchItem?>()
    lateinit var movieAdapter : MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_template)



        initToolbar()
//        btn_search.setOnClickListener ({
            RetrofitConfig().getService()
                .getAll()
                .enqueue(object : Callback<NewsResponse>{
                    override fun onResponse(
                        call: Call<NewsResponse>,
                        response: Response<NewsResponse>
                    ) {
                        val data:NewsResponse? = response.body()
                      dataGenerate(data?.data?.adlNews as List<AdlNewsItem>)
                    }

                    override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                        Toast.makeText(this@MainActivity,t.localizedMessage,Toast.LENGTH_LONG).show()
                    }

                })
//        })
    }

    fun initToolbar() {

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24)

        if (toolbar.navigationIcon != null) {
            toolbar.navigationIcon?.setColorFilter(ContextCompat.getColor(this, R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP)
        }

        toolbar.title = "Expandable List"

        try {
            toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.md_white_1000))
        } catch (e: Exception) {
            Log.e("TEAMPS", "Can't set color.")
        }

        try {
            setSupportActionBar(toolbar)
        } catch (e: Exception) {
            Log.e("TEAMPS", "Error in set support action bar.")
        }

        try {
            if (supportActionBar != null) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
        } catch (e: Exception) {
            Log.e("TEAMPS", "Error in set display home as up enabled.")
        }

    }

    fun dataGenerate ( listNews :List<AdlNewsItem>){
        listDataHeader = ArrayList()
        listDataChild = HashMap()

        listDataHeader.add("Result Movie")
        listDataChild["Result Movie"] = listNews

        newsAdapter = NewsAdapter(this, listDataHeader, listDataChild)

        lvExp.setAdapter(newsAdapter)

    }


}