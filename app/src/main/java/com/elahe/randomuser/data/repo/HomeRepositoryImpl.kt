package com.elahe.randomuser.data.repo

import com.elahe.randomuser.data.User
import com.elahe.randomuser.data.repo.source.HomeDataSource
import io.reactivex.Single

class HomeRepositoryImpl(val homeRemoteDataSource: HomeDataSource) : HomeRepository {
    override fun getUsers(): Single<User> =
        homeRemoteDataSource.getUsers()
}