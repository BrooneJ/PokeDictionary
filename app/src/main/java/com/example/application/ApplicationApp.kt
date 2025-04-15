package com.example.application

import android.app.Application
import com.example.application.di.appModule
import com.example.content.network.di.networkModule
import com.example.content.presentation.di.pokemonPresentationModule
import com.example.core.data.di.coreDataModule
import com.example.core.database.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class ApplicationApp : Application() {
  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }

    startKoin {
      androidLogger()
      androidContext(this@ApplicationApp)
      modules(
        appModule,
        databaseModule,
        networkModule,
        coreDataModule,
        pokemonPresentationModule
      )
    }
  }
}