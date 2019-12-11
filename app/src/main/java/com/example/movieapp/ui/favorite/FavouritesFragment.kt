package com.example.movieapp.ui.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.ui.BaseFragment
import com.example.movieapp.ui.MainActivity
import com.example.movieapp.ui.Router
import com.example.movieapp.ui.movies.view.MovieViewModel
import kotlinx.android.synthetic.main.favourite_movies_layout.view.*
import org.koin.android.ext.android.get
import org.koin.android.ext.android.getKoin
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

class FavouritesFragment : BaseFragment(), FavouritesContract.FavouritesView,
    FavouriteMoviesAdapter.OnItemClickListener {

    companion object {
        private const val ROUTER_SCOPE_ID = "MAIN_SCOPE"
        private const val SCOPE_ID = "FAVOURITE_SCOPE_ID"
    }

    private val routerScope = getKoin().getOrCreateScope(ROUTER_SCOPE_ID, named<MainActivity>())
    private val router: Router by routerScope.inject()


    private val scope = getKoin().getOrCreateScope(SCOPE_ID, named<FavouritesFragment>())
    private val favouritesPresenter: FavouritesPresenter by scope.inject { parametersOf(this) }

    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private val moviesAdapter by lazy {
        FavouriteMoviesAdapter(
            get(),
            get(),
            get()
        )
    }
    private lateinit var fragmentView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.favourite_movies_layout, container, false)
        setPresenter(favouritesPresenter)


        linearLayoutManager = LinearLayoutManager(this.context)
        moviesAdapter.setListener(this)

        fragmentView = view
        return view
    }

    override fun onItemClick(movie: MovieViewModel) {
        if (this.context != null) {
            router.showDetails(movie.id)
        }
    }

    override fun showFavouriteMovies(movies: List<MovieViewModel>) {
        moviesAdapter.setData(movies)

        fragmentView.recyclerViewFavourites.apply {
            layoutManager = linearLayoutManager
            adapter = moviesAdapter
        }
    }

    override fun onError(throwable: Throwable) {
        Log.e("ERROR", "Reason: " + throwable.localizedMessage)
        Toast.makeText(this.context, "Unable to fetch movies: " + throwable.localizedMessage, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        scope.close()
        super.onDestroy()
    }
}