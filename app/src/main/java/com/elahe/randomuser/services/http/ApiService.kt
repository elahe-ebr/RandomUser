package com.elahe.randomuser.services.http

import com.elahe.randomuser.data.User
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(".")
    fun getUsers(@Query("format") format: String, @Query("results") results: Int): Single<User>
}


fun createApiServiceInstance(): ApiService {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me/api/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(ApiService::class.java)
}
