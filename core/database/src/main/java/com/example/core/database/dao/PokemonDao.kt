package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.example.core.database.entity.PokemonEntity

@Dao
interface PokemonDao {

  @Upsert
  suspend fun upsertPokemon(pokemon: PokemonEntity)
}