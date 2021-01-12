package com.github.coutinhonobre.githubapi

import android.app.Application
import com.github.coutinhonobre.githubapi.dependency.apiModule
import com.github.coutinhonobre.githubapi.dependency.repositoryModule
import com.github.coutinhonobre.githubapi.dependency.retrofitModule
import com.github.coutinhonobre.githubapi.dependency.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GitHubApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@GitHubApplication)
            modules(listOf(repositoryModule, viewModelModule, retrofitModule, apiModule))
        }
    }
}