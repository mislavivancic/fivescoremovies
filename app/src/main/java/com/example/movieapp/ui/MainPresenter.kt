package com.example.movieapp.ui

import com.example.movieapp.data.presenter.BasePresenter
import org.koin.core.KoinComponent
import org.koin.core.qualifier.named

class MainPresenter(private val activity: BaseActivity) : BasePresenter(), KoinComponent {

    companion object {
        private const val SCOPE_ID = "MAIN_SCOPE"
    }

    private val scope = getKoin().getOrCreateScope(SCOPE_ID, named<MainActivity>())

    private val router: Router by scope.inject()

    override fun activate() {
        router.setActivity(activity)
        router.showMovieList()
    }

    fun showMovieList() {
        router.navigateMovieList()

    }

    fun showFavorites() {
        router.navigateFavorites()
    }

    fun onBack() {
        router.goBack()
    }

}