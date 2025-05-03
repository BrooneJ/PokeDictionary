package com.example.core.domain.content

import com.example.core.model.Pokemon
import com.example.core.model.PokemonDetails
import kotlinx.coroutines.flow.Flow

interface PokeRepository {
  fun fetchPokemons(page: Int): Flow<List<Pokemon>>
  suspend fun fetchPokemonDetails(name: String): PokemonDetails?
}