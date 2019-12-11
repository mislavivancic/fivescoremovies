package com.example.movieapp.data.presenter

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter {

    private val compositeDisposable = CompositeDisposable()

    abstract fun activate()

    fun deactivate() {
        compositeDisposable.clear()
    }

    fun addSubscription(disposable: Disposable) = compositeDisposable.add(disposable)

}