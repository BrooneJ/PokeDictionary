package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.database.entity.PokemonDetailsEntity

@Dao
interface PokemonDetailsDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertPokemonDetails(pokemonDetails: PokemonDetailsEntity)

  @Query("SELECT * FROM PokemonDetailsEntity WHERE name = :name_")
  suspend fun getPokemonDetails(name_: String): PokemonDetailsEntity
}