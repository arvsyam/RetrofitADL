package com.adl.retrofitadl.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.movie_holder.view.*

class MovieVH(view: View): RecyclerView.ViewHolder(view) {
    val  title = view.txtTitle
    val director = view.txtDirector
    val rating = view.txtRating
//    val image = view.photo
//    val favourite = view.btnFavourite


    fun bindData(adapter:MovieAdapter, position:Int){

        title.setText(adapter.data.get(position)?.title)
        director.setText(adapter.data.get(position)?.year)
        rating.setText(adapter.data.get(position)?.type)



    }
}