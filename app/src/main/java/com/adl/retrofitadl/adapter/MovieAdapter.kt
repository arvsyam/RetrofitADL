package com.adl.retrofitadl.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adl.retrofitadl.R
import com.adl.retrofitadl.model.OMDBResponse

class MovieAdapter(val data: ArrayList<OMDBResponse>): RecyclerView.Adapter<MovieVH>() {
    lateinit var parent:ViewGroup
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        this.parent = parent

        return MovieVH(LayoutInflater.from(parent.context).inflate(R.layout.movie_holder,parent,false))
    }

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        holder.bindData(this@MovieAdapter,position)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}