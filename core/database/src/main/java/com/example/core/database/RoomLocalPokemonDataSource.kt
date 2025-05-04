package com.example.core.database

import com.example.core.database.dao.PokemonDao
import com.example.core.database.dao.PokemonDetailsDao
import com.example.core.database.entity.mapper.asDomain
import com.example.core.database.entity.mapper.asEntity
import com.example.core.domain.content.LocalPokemonDataSource
import com.example.core.domain.util.DataError
import com.example.core.domain.util.Result
import com.example.core.model.Pokemon
import com.example.core.model.PokemonDetails

class RoomLocalPokemonDataSource(
  private val pokemonDao: PokemonDao,
  private val pokemonDetailsDao: PokemonDetailsDao
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

  override suspend fun insertPokemonDetails(pokemonDetails: PokemonDetails) {
    pokemonDetailsDao.insertPokemonDetails(pokemonDetails.asEntity())
  }

  override suspend fun fetchPokemonDetails(name: String): Result<PokemonDetails, DataError.Local> {
    return try {
      val pokemonDetails = pokemonDetailsDao.getPokemonDetails(name)
      if (pokemonDetails == null) {
        return Result.Success(
          PokemonDetails(
            id = 0,
            name = "",
            height = 0,
            weight = 0,
            experience = 0,
            types = emptyList(),
            stats = emptyList()
          )
        )
      }
      Result.Success(pokemonDetails.asDomain())
    } catch (e: Exception) {
      Result.Error(DataError.Local.UNKNOWN)
    }
  }
}