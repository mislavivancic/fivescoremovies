package com.example.movieapp.ui.movies

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.main_list.view.*
import org.koin.android.ext.android.get
import org.koin.android.ext.android.getKoin
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import java.util.concurrent.TimeUnit

class MainFragment : MoviesAdapter.OnItemClickListener, MoviesContract.MovieView, BaseFragment() {

    companion object {
        private const val ROUTER_SCOPE_ID = "MAIN_SCOPE"
        private const val SCOPE_ID = "MAIN_SCOPE_ID"
        private const val DEBOUNCE_DURATION: Long = 500
        private const val INTERNET_CONNECTION_ERROR = "Check internet connection"
        private const val LOAD_OFFSET = 4
    }

    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private val moviesAdapter by lazy {
        MoviesAdapter(
            get(),
            get(),
            get()
        )
    }

    private val routerScope = getKoin().getOrCreateScope(ROUTER_SCOPE_ID, named<MainActivity>())
    private val router: Router by routerScope.inject()

    private val scope = getKoin().getOrCreateScope(SCOPE_ID, named<MainFragment>())
    private val moviePresenter: MoviePresenter by scope.inject { parametersOf(this) }
    private lateinit var fragmentView: View
    private val compositeDisposable = CompositeDisposable()
    private val searchObservable = getSearchObservable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.main_list, container, false)
        fragmentView = view
        setPresenter(moviePresenter)

        linearLayoutManager = LinearLayoutManager(this.context)
        moviesAdapter.setListener(this)

        view.swipeRefreshLayout.setOnRefreshListener {
            moviePresenter.getMovies("")
        }

        initRecyclerView()
        return view
    }

    private fun initRecyclerView() {
        fragmentView.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if ((linearLayoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() >= linearLayoutManager.itemCount - LOAD_OFFSET) {
                    moviePresenter.getNextPageMovies()
                }
            }
        })
    }

    override fun onItemClick(movie: MovieViewModel) {
        if (this.context != null) {
            router.showDetails(movie.id)
        }
    }

    override fun onFavourite(movie: MovieViewModel) {
        moviePresenter.saveMovie(movie)
    }

    override fun onUnfavoured(movieId: Int) {
        moviePresenter.unfavouredMovie(movieId)
    }

    override fun showMovies(movies: List<MovieViewModel>) {
        fragmentView.swipeRefreshLayout.isRefreshing = false
        moviesAdapter.setData(movies)

        fragmentView.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = moviesAdapter
        }
    }

    override fun showNextPage(movies: List<MovieViewModel>) {
        moviesAdapter.addData(movies)
    }

    override fun onErrorMovies(t: Throwable) {
        Log.e("ERROR", "Reason: " + t.localizedMessage)
        Toast.makeText(this.context, INTERNET_CONNECTION_ERROR, Toast.LENGTH_LONG).show()
    }

    private fun getSearchObservable() = Flowable.create<String>(
        { emitter ->
            fragmentView.queryField.addTextChangedListener(
                object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {}

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        emitter.onNext(p0.toString())
                    }

                })
        }, BackpressureStrategy.LATEST
    ).distinctUntilChanged()
        .switchMap { Flowable.just(it) }

    override fun onResume() {
        super.onResume()
        compositeDisposable.add(searchObservable
            .debounce(DEBOUNCE_DURATION, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { moviePresenter.getMovies(it) })

    }

    override fun onDestroy() {
        scope.close()
        super.onDestroy()
    }
}
