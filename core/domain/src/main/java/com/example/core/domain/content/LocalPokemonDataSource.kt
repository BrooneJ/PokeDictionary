package com.example.core.domain.content

interface LocalPokemonDataSource {
  suspend fun upsertPokemon(pokemon: Pokemon)
}