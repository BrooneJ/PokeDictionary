package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.core.database.entity.PokemonEntity

@Dao
interface PokemonDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertPokemon(pokemonList: List<PokemonEntity>)
}