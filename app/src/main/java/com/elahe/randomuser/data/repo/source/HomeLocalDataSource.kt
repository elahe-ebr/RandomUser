package com.elahe.randomuser.data.repo.source

import com.elahe.randomuser.data.User
import io.reactivex.Single

class HomeLocalDataSource:HomeDataSource {
    override fun getUsers(): Single<User> {
        TODO("Not yet implemented")
    }
}