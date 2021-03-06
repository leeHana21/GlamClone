package com.github.hanalee.glamclone

import android.app.Application
import com.github.hanalee.glamclone.di.module.adapterModule
import com.github.hanalee.glamclone.di.module.repositoryModule
import com.github.hanalee.glamclone.di.module.viewModelModule


import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Application class
 * Koin 모듈 등록
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    adapterModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }

    }
}