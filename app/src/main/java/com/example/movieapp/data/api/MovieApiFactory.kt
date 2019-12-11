package com.example.movieapp.data.api

class MovieApiFactory {

    companion object {

        const val key = "ae87bcbe0faa421f96c463e53ed4977c"

        fun getApi(): MovieApiClient {
            return RetrofitFactory.provideRetrofit().create(MovieApiClient::class.java)
        }
    }
}