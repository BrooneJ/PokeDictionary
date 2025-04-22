package com.example.core.data.content

import com.example.core.database.RoomLocalPokemonDataSource
import com.example.core.domain.content.PokeRepository
import com.example.core.domain.content.Pokemon
import com.example.core.domain.content.RemotePokemonDataSource
import com.example.core.domain.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class OfflineFirstPokeRepository(
  private val remotePokeDataSource: RemotePokemonDataSource,
  private val localPokemonDataSource: RoomLocalPokemonDataSource,
) : PokeRepository {

  override fun fetchPokemons(page: Int): Flow<List<Pokemon>> = flow {
    when (val pokemonList = localPokemonDataSource.fetchPokemonList(page)) {
      is Result.Error -> {
        emit(emptyList())
      }

      is Result.Success -> {
        if (pokemonList.data.isEmpty()) {
          when (val remotePokemonListData = remotePokeDataSource.getPokemonList(page)) {
            is Result.Error -> emit(emptyList())
            is Result.Success -> {
              localPokemonDataSource.insertPokemon(remotePokemonListData.data.onEach {
                it.page = page
              })
              when (val localPokemonList = localPokemonDataSource.getAllPokemonList(page)) {
                is Result.Error -> emit(emptyList())
                is Result.Success -> {
                  emit(localPokemonList.data)
                }
              }
            }
          }
        } else {
          emit(pokemonList.data)
        }
      }
    }
  }.flowOn(Dispatchers.IO)
}