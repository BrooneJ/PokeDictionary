package com.example.core.database.di

import androidx.room.Room
import com.example.core.database.RoomLocalPokemonDataSource
import com.example.core.database.PokemonDatabase
import com.example.core.domain.content.LocalPokemonDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val databaseModule = module {
  single {
    Room.databaseBuilder(
      androidApplication(),
      PokemonDatabase::class.java,
      "sample.db"
    ).build()
  }
  single { get<PokemonDatabase>().pokemonDao() }

  singleOf(::RoomLocalPokemonDataSource).bind<LocalPokemonDataSource>()
}