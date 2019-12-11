package com.example.movieapp.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.data.api.ImageApi
import com.example.movieapp.ui.movies.view.MovieViewModel
import com.example.movieapp.ui.util.AnimationLoader
import com.example.movieapp.ui.util.ImageLoader
import kotlinx.android.synthetic.main.text_row_item.view.*

class FavouriteMoviesAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    private val animationLoader: AnimationLoader
) :
    RecyclerView.Adapter<FavouriteMoviesAdapter.MovieViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(movie: MovieViewModel)
    }

    private val movies = mutableListOf<MovieViewModel>()
    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = layoutInflater.inflate(R.layout.text_row__favourite_item, parent, false)
        return MovieViewHolder(view, onItemClickListener, imageLoader, animationLoader)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.addValuesOnHolder(movies[position], position)
    }

    override fun getItemCount() = movies.size

    fun setListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun setData(movieList: List<MovieViewModel>) {
        movies.clear()
        movies.addAll(movieList.toMutableList())
        notifyDataSetChanged()
    }

    class MovieViewHolder(
        private val view: View,
        private val onItemClickListener: OnItemClickListener?,
        private val imageLoader: ImageLoader,
        private val animationLoader: AnimationLoader
    ) :
        RecyclerView.ViewHolder(view) {

        private var lastPosition = -1

        fun addValuesOnHolder(movie: MovieViewModel, position: Int) = movie.run {
            imageLoader.loadImage(ImageApi.imageBaseURL + posterPath, view.imagePosterView)
            view.setOnClickListener { onItemClickListener?.onItemClick(this) }
            view.ratingBarList.rating = voteAverage.toFloat()
            view.titleTextView.text = title
            view.popularityTextView.text = popularity.toString()
            setAnimation(position)
        }

        private fun setAnimation(position: Int) {
            if (position > lastPosition) {
                val animation = animationLoader.loadAnimation(android.R.anim.slide_in_left)
                view.startAnimation(animation)
                lastPosition = position
            }
        }
    }
}