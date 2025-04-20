package com.example.core.domain.content

interface LocalPokemonDataSource {
  suspend fun insertPokemon(pokemon: Pokemon)
}