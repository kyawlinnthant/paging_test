package com.kyawlinnthant.pagingtest.app

import android.app.Application
import com.kyawlinnthant.pagingtest.di.appModule
import com.kyawlinnthant.pagingtest.di.dataModule
import org.koin.android.ext.android.startKoin

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(dataModule, appModule))
    }
}