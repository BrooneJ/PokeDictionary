package com.example.core.domain.content

import kotlinx.coroutines.flow.Flow

interface PokeRepository {
  fun fetchPokemons(page: Int): Flow<List<Pokemon>>
}