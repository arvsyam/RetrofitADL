package com.adl.retrofitadl

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.adl.retrofitadl.model.AdlNewsItem
import com.adl.retrofitadl.model.NewsResponse
import com.adl.retrofitadl.model.PostNewsResponse
import com.adl.retrofitadl.services.RetrofitConfig
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.github.drjacky.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_create_news.*
import kotlinx.android.synthetic.main.activity_main_template.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class CreateNews : AppCompatActivity() {
    lateinit var photoURI: Uri
    var isUpdate = false
    lateinit var newsUpdate: AdlNewsItem

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val uri = it.data?.data!!
            photoURI = uri
            imageView.setImageURI(uri)
            Log.d("uri image","${uri}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_news)
        var news =intent?.getParcelableExtra<AdlNewsItem>("data")
        if(news != null){
            isUpdate = true
            newsUpdate = news
            photoURI = Uri.parse(news.image)

            et_news_title.setText(news!!.title)
            et_news_content.setText(news!!.body)
//            imageView.setImageURI(photoURI)
            imageView.let {
                Glide.with(this)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .load(news.image)
                    .into(it)


            }
        }

        initComponent()

    }

    fun initComponent(){
        btn_upload_image.setOnClickListener({
            Log.d("button click","btn_image")
            pickImage()
        })

        btn_save.setOnClickListener({
            if(isUpdate){
                updateData(newsUpdate)
            }else{
                saveData()
            }

        })
    }



    fun saveData(){
        RetrofitConfig().getService()
            .addNews(createRB(et_news_title.text.toString()),createRB(et_news_content.text.toString()), uploadImage(photoURI,"image"))
            .enqueue(object : Callback<PostNewsResponse>{
                override fun onResponse(
                    call: Call<PostNewsResponse>,
                    response: Response<PostNewsResponse>
                ) {
                    if(response.isSuccessful()){
                        Toast.makeText(this@CreateNews,"Saved", Toast.LENGTH_LONG).show()
                        finish()
                    }else{
                        response.body().toString()
                        Toast.makeText(this@CreateNews,"not Saved", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<PostNewsResponse>, t: Throwable) {
                    Log.e("error request",t.localizedMessage,t)
                }

            })
    }

    fun updateData(news:AdlNewsItem){


        RetrofitConfig().getService()
            .updateNews(createRB(news.id.toString()),createRB(et_news_title.text.toString()),createRB(et_news_content.text.toString()), uploadImage(photoURI,"image"))
            .enqueue(object : Callback<PostNewsResponse>{
                override fun onResponse(
                    call: Call<PostNewsResponse>,
                    response: Response<PostNewsResponse>
                ) {
                    if(response.isSuccessful()){
                        Toast.makeText(this@CreateNews,"Saved", Toast.LENGTH_LONG).show()
                        finish()
                    }else{
                        response.body().toString()
                        Toast.makeText(this@CreateNews,"not Saved", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<PostNewsResponse>, t: Throwable) {
                    Log.e("error request",t.localizedMessage,t)
                }

            })
    }

    fun pickImage(){
        cameraLauncher.launch(
            ImagePicker.with(this)
                .crop()
                .cameraOnly()
                .maxResultSize(480, 800, true)
                .createIntent()
        )
    }
    fun createRB(data:String): RequestBody {
        return RequestBody.create(MultipartBody.FORM,data)
    }

    fun uploadImage(uri:Uri,param:String): MultipartBody.Part {
        val file: File = File(uri.path)
        val rb: RequestBody =  file.asRequestBody("image/jpeg".toMediaTypeOrNull())

        return MultipartBody.Part.createFormData(param,file.name,rb)

    }







}