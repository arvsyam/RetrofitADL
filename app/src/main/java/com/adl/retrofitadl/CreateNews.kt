package com.adl.retrofitadl

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adl.retrofitadl.model.AdlNewsItem
import com.adl.retrofitadl.services.RetrofitConfig
import kotlinx.android.synthetic.main.activity_create_news.*
import kotlinx.android.synthetic.main.activity_main_template.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateNews : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_news)


        btn_save.setOnClickListener({


            RetrofitConfig().getService()
                .saveNews(createNews())
                .enqueue(object : Callback<AdlNewsItem> {
                    override fun onResponse(
                        call: Call<AdlNewsItem>,
                        response: Response<AdlNewsItem>
                    ) {
                        if(response.isSuccessful()){
                            Toast.makeText(this@CreateNews,"Saved",Toast.LENGTH_LONG).show()
                        }else{
                            response.body().toString()
                            Toast.makeText(this@CreateNews,"not Saved",Toast.LENGTH_LONG).show()
                        }
                    }
                    override fun onFailure(call: Call<AdlNewsItem>, t: Throwable) {
                        Toast.makeText(this@CreateNews,t.localizedMessage, Toast.LENGTH_LONG).show()
                    }

                })
        })



    }

    fun createNews():AdlNewsItem{
        val title: String = et_news_title.getText().toString()
        val body: String = et_news_content.getText().toString()

        var newsitem = AdlNewsItem(" - ", "1",title,body)
        Toast.makeText(this@CreateNews,"title ${newsitem.title}",Toast.LENGTH_LONG).show()
        Toast.makeText(this@CreateNews,"body ${newsitem.body}",Toast.LENGTH_LONG).show()
        Toast.makeText(this@CreateNews,"image ${newsitem.image}",Toast.LENGTH_LONG).show()

        return newsitem
    }



}