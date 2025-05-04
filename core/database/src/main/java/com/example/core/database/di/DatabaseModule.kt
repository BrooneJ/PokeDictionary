package com.example.core.database.di

import androidx.room.Room
import com.example.core.database.PokemonDatabase
import com.example.core.database.RoomLocalPokemonDataSource
import com.example.core.database.StatsResponseConverter
import com.example.core.database.TypeResponseConverter
import com.example.core.domain.content.LocalPokemonDataSource
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val databaseModule = module {
  single { Json { ignoreUnknownKeys = true } }
  
  singleOf(::StatsResponseConverter)
  singleOf(::TypeResponseConverter)

  single {
    Room.databaseBuilder(
      androidApplication(),
      PokemonDatabase::class.java,
      "pokemon.db"
    )
      .fallbackToDestructiveMigration()
      .addTypeConverter(get<TypeResponseConverter>())
      .addTypeConverter(get<StatsResponseConverter>())
      .build()
  }
  single { get<PokemonDatabase>().pokemonDao() }
  single { get<PokemonDatabase>().pokemonDetailsDao() }

  singleOf(::RoomLocalPokemonDataSource).bind<LocalPokemonDataSource>()
}