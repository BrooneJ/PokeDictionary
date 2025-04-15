package com.example.core.domain.content

interface PokeRepository {
  suspend fun fetchPokemons(page: Int): List<Pokemon>
}