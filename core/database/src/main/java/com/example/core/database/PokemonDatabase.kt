package com.example.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.database.dao.PokemonDao
import com.example.core.database.entity.PokemonEntity

@Database(
  entities = [PokemonEntity::class],
  version = 1
)
abstract class PokemonDatabase : RoomDatabase() {
  
  abstract fun pokemonDao(): PokemonDao
}