package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.database.entity.PokemonEntity

@Dao
interface PokemonDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertPokemon(pokemonList: List<PokemonEntity>)

  @Query("SELECT * FROM PokemonEntity WHERE page = :page_")
  suspend fun getPokemonList(page_: Int): List<PokemonEntity>

  @Query("SELECT * FROM PokemonEntity WHERE page <= :page_")
  suspend fun getAllPokemonList(page_: Int): List<PokemonEntity>
}