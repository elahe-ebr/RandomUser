package com.elahe.randomuser.feature.home

import androidx.lifecycle.MutableLiveData
import com.elahe.randomuser.common.BaseViewModel
import com.elahe.randomuser.common.CustomSingleObserver
import com.elahe.randomuser.common.asyncNetworkRequest
import com.elahe.randomuser.data.Result
import com.elahe.randomuser.data.User
import com.elahe.randomuser.data.repo.HomeRepository
import com.elahe.randomuser.database.AppDatabase
import com.elahe.randomuser.database.UserDao
import timber.log.Timber

class HomeViewModel(private val homeRepository: HomeRepository, appDatabase: AppDatabase) :
    BaseViewModel() {

    var userListLiveData = MutableLiveData<MutableList<Result>>()
    private var userDao: UserDao = appDatabase.userDao()
    private var dbUser: MutableList<Result> = ArrayList()

    init {
        dbUser = userDao.get()
        if (dbUser.isNotEmpty())
            userListLiveData.value = dbUser
        getUsers()
    }

    private fun getUsers() {
        if (dbUser.isEmpty())
            progressBarLiveData.value = true
        homeRepository.getUsers()
            .asyncNetworkRequest()
            .doFinally { progressBarLiveData.value = false }
            .subscribe(object : CustomSingleObserver<User>(compositeDisposable) {
                override fun onSuccess(t: User) {
                    userDao.delete(t.results)
                    try {
                        t.results.forEach {
                            userDao.insert(it)
                        }

                    } catch (exception: Exception) {
                        Timber.e(exception)
                    }
                    userListLiveData.value = t.results
                }
            })
    }
}