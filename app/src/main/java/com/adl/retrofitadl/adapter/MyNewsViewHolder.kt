package com.adl.retrofitadl.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.news_holder.view.*

class MyNewsViewHolder(view: View):RecyclerView.ViewHolder(view) {
    val image = view.news_image
    val newsTitle = view.txtNewsTitle
    val newsBody = view.txtNewsBody

    val edit = view.btn_edit
    val delete = view.btn_delete

    fun bindData(adapter:MyNewsAdapter, position:Int){
        newsTitle.setText(adapter.data.get(position)?.title)
        Log.d("bind","${adapter.data.get(position)?.title}")
        newsBody.setText(adapter.data.get(position)?.body)
        image?.let {
            Glide.with(adapter.parent.context)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .load(adapter.data.get(position)?.image)
                .into(it)


        }

        delete.setOnClickListener({
            adapter.delete(position)
        })
        edit.setOnClickListener({
            adapter.updateNews(position)
        })

    }
}