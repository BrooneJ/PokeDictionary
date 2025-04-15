package com.example.core.data.content

import com.example.core.domain.content.PokeRepository
import com.example.core.domain.content.Pokemon
import com.example.core.domain.content.RemotePokemonDataSource
import com.example.core.domain.util.Result

class OfflineFirstPokeRepository(
  private val remotePokeDataSource: RemotePokemonDataSource,
): PokeRepository {

  override suspend fun fetchPokemons(page: Int): List<Pokemon> {
    remotePokeDataSource.getPokemonList(page).let { result ->
      return when (result) {
        is Result.Error -> emptyList()
        is Result.Success -> result.data
      }
    }
  }
}