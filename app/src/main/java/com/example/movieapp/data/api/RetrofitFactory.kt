package com.example.movieapp.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitFactory {

    companion object {

        private const val baseURL = "https://api.themoviedb.org/3/"

        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(baseURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .client(OkHttpClient.Builder().build())
                .build()
        }

        //initializing retrofit
        fun provideRetrofit(): Retrofit {
            return retrofit
        }
    }
}