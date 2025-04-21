package com.example.core.data.content

import com.example.core.database.RoomLocalPokemonDataSource
import com.example.core.domain.content.PokeRepository
import com.example.core.domain.content.Pokemon
import com.example.core.domain.content.RemotePokemonDataSource
import com.example.core.domain.util.DataError
import com.example.core.domain.util.Result

class OfflineFirstPokeRepository(
  private val remotePokeDataSource: RemotePokemonDataSource,
  private val localPokemonDataSource: RoomLocalPokemonDataSource
) : PokeRepository {

  override suspend fun fetchPokemons(page: Int): List<Pokemon> {
    return when (val pokemonList = localPokemonDataSource.fetchPokemonList(page)) {
      is Result.Error -> {
        return when (pokemonList.error) {
          DataError.Local.DISK_FULL -> emptyList()
          DataError.Local.UNKNOWN -> emptyList()
          else -> emptyList()
        }
      }

      is Result.Success -> {
        if (pokemonList.data.isEmpty()) {
          val results = remotePokeDataSource.getPokemonList(page).let { result ->
            return@let when (result) {
              is Result.Error -> emptyList()
              is Result.Success -> result.data
            }
          }
          results.forEach { result ->
            result.page = page
          }
          localPokemonDataSource.insertPokemon(results)
          return when (val pokemons = localPokemonDataSource.getAllPokemonList(page)) {
            is Result.Error -> emptyList()
            is Result.Success -> pokemons.data
          }
        } else {
          return pokemonList.data
        }
      }
    }
  }
}