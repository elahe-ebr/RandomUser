package com.elahe.randomuser.data.repo.source

import com.elahe.randomuser.data.User
import io.reactivex.Single

interface HomeDataSource {
    fun getUsers(): Single<User>
}