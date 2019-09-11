package com.ny.mvvmtest.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CustomViewBindings {

    companion object{
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(view: ImageView, imageurl: String) {
            Picasso.with(view.context)
                .load(imageurl)
                .into(view)
        }

        @BindingAdapter("setAdapter")
        @JvmStatic
        fun bindRecyclerViewAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            recyclerView.adapter = adapter
        }
    }

}