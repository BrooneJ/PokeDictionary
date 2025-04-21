package com.example.core.domain.content

interface LocalPokemonDataSource {
  suspend fun fetchPokemon(pokemon: Pokemon)
}