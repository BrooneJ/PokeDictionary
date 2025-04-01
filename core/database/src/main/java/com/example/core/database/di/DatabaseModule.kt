package com.example.core.database.di

import androidx.room.Room
import com.example.core.database.RoomLocalSampleDataSource
import com.example.core.database.SampleDatabase
import com.example.core.domain.content.LocalSampleDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val databaseModule = module {
  single {
    Room.databaseBuilder(
      androidApplication(),
      SampleDatabase::class.java,
      "sample.db"
    ).build()
  }
  single { get<SampleDatabase>().sampleDao() }

  singleOf(::RoomLocalSampleDataSource).bind<LocalSampleDataSource>()
}