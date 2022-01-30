package com.elahe.randomuser

import android.app.Application
import com.elahe.randomuser.data.repo.HomeRepository
import com.elahe.randomuser.data.repo.HomeRepositoryImpl
import com.elahe.randomuser.data.repo.source.HomeRemoteDataSource
import com.elahe.randomuser.database.createDataBaseInstance
import com.elahe.randomuser.feature.home.HomeViewModel
import com.elahe.randomuser.feature.home.UserListAdapter
import com.elahe.randomuser.services.FrescoImageLoadingService
import com.elahe.randomuser.services.ImageLoadingService
import com.elahe.randomuser.services.http.createApiServiceInstance
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        Timber.plant(Timber.DebugTree())

        val myModules = module {

            single { createApiServiceInstance() }
            single { createDataBaseInstance(this@App) }
            single<ImageLoadingService> { FrescoImageLoadingService() }

            factory<HomeRepository> { HomeRepositoryImpl(HomeRemoteDataSource(get())) }
            factory { UserListAdapter(this@App, get()) }

            viewModel { HomeViewModel(get(), get()) }
        }
        startKoin {
            androidContext(this@App)
            modules(myModules)
        }
    }
}