package com.example.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.core.database.dao.PokemonDao
import com.example.core.database.dao.PokemonDetailsDao
import com.example.core.database.entity.PokemonDetailsEntity
import com.example.core.database.entity.PokemonEntity

@Database(
  entities = [PokemonEntity::class, PokemonDetailsEntity::class],
  version = 1
)
@TypeConverters(value = [TypeResponseConverter::class, StatsResponseConverter::class])
abstract class PokemonDatabase : RoomDatabase() {

  abstract fun pokemonDao(): PokemonDao
  abstract fun pokemonDetailsDao(): PokemonDetailsDao
}