package com.example.core.database

import com.example.core.database.dao.PokemonDao
import com.example.core.database.entity.mapper.asDomain
import com.example.core.database.entity.mapper.asEntity
import com.example.core.domain.content.LocalPokemonDataSource
import com.example.core.domain.content.Pokemon
import com.example.core.domain.util.DataError
import com.example.core.domain.util.Result

class RoomLocalPokemonDataSource(
  private val pokemonDao: PokemonDao
) : LocalPokemonDataSource {

  override suspend fun fetchPokemonList(page: Int): Result<List<Pokemon>, DataError.Local> {
    return try {
      val pokemonList = pokemonDao.getPokemonList(page)
      Result.Success(pokemonList.asDomain())
    } catch (e: Exception) {
      Result.Error(DataError.Local.UNKNOWN)
    }
  }

  override suspend fun insertPokemon(pokemonList: List<Pokemon>) {
    pokemonDao.insertPokemon(pokemonList.asEntity())
  }

  override suspend fun getAllPokemonList(page: Int): Result<List<Pokemon>, DataError.Local> {
    return try {
      val pokemonList = pokemonDao.getAllPokemonList(page)
      Result.Success(pokemonList.asDomain())
    } catch (e: Exception) {
      Result.Error(DataError.Local.UNKNOWN)
    }
  }
}