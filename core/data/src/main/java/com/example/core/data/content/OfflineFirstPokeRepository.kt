package com.example.core.data.content

import com.example.core.database.RoomLocalPokemonDataSource
import com.example.core.domain.content.PokeRepository
import com.example.core.domain.content.RemotePokemonDataSource
import com.example.core.domain.util.Result
import com.example.core.model.Pokemon
import com.example.core.model.PokemonDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

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
        Timber.d("Pokemon list from local data source: ${pokemonList.data}")
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

  override fun fetchPokemonDetails(name: String): Flow<PokemonDetails?> = flow {
    when (localPokemonDataSource.fetchPokemonDetails(name)) {
      is Result.Error -> (emit(null))
      is Result.Success -> {
        when (val remotePokemonDetails = remotePokeDataSource.getPokemonByName(name)) {
          is Result.Error -> emit(null)
          is Result.Success -> {
            localPokemonDataSource.insertPokemonDetails(remotePokemonDetails.data)
            when (val localPokemonDetails = localPokemonDataSource.fetchPokemonDetails(name)) {
              is Result.Error -> emit(null)
              is Result.Success -> {
                emit(localPokemonDetails.data)
              }
            }
          }
        }
      }
    }
  }.flowOn(Dispatchers.IO)
}