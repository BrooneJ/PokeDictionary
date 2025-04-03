package com.example.core.database

import com.example.core.database.dao.PokemonDao
import com.example.core.domain.content.LocalPokemonDataSource
import com.example.core.domain.content.Pokemon

class RoomLocalPokemonDataSource(
  private val pokemonDao: PokemonDao
) : LocalPokemonDataSource {

  override suspend fun upsertPokemon(pokemon: Pokemon) {
    TODO("Not yet implemented")
  }
}