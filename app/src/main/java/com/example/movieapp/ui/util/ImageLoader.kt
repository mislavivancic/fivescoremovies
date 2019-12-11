package com.example.movieapp.ui.util

import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.movieapp.R

class ImageLoader(val glide: RequestManager) {

    fun loadImage(url: String, imageView: ImageView) {
        this.glide.load(url).error(R.drawable.no_movie).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(imageView)
    }
}