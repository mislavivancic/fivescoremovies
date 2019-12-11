package com.example.movieapp.ui.util

import android.content.Context
import android.view.animation.AnimationUtils

class AnimationLoader(val context: Context) {

    fun loadAnimation(id: Int) = AnimationUtils.loadAnimation(context, id)
}