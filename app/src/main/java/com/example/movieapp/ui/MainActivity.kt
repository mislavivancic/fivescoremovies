package com.example.movieapp.ui

import android.os.Bundle
import android.view.KeyEvent
import com.example.movieapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.getKoin
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

class MainActivity : BaseActivity() {

    companion object {
        private const val SCOPE_ID = "MAIN_SCOPE"
    }

    private val scope = getKoin().getOrCreateScope(SCOPE_ID, named<MainActivity>())
    private val mainPresenter: MainPresenter by scope.inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        setPresenter(mainPresenter)


        bottom_navigation.setOnNavigationItemSelectedListener(getNavigationListener())
    }

    private fun getNavigationListener() = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.action_favorites -> {
                mainPresenter.showFavorites()
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_list -> {
                mainPresenter.showMovieList()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event?.repeatCount == 0) {
            mainPresenter.onBack()
            return true
        }

        return super.onKeyDown(keyCode, event)
    }
    override fun onDestroy() {
        scope.close()
        super.onDestroy()
    }
}
