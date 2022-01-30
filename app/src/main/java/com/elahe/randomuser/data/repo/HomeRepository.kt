package com.elahe.randomuser.data.repo

import com.elahe.randomuser.data.User
import io.reactivex.Single

interface HomeRepository {
    fun getUsers(): Single<User>
}