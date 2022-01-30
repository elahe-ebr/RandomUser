package com.elahe.randomuser.data.repo.source

import com.elahe.randomuser.data.User
import com.elahe.randomuser.services.http.ApiService
import io.reactivex.Single

class HomeRemoteDataSource(val apiService: ApiService) : HomeDataSource {
    override fun getUsers(): Single<User> = apiService.getUsers("Json", 20)
}