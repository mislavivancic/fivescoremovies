package com.example.movieapp.ui

import androidx.fragment.app.Fragment
import com.example.movieapp.data.presenter.BasePresenter

abstract class BaseFragment : Fragment() {

    private lateinit var presenter: BasePresenter

    fun setPresenter(presenter: BasePresenter) {
        this.presenter = presenter
    }

    override fun onStart() {
        super.onStart()
        presenter.activate()
    }

    override fun onStop() {
        presenter.deactivate()
        super.onStop()
    }
}