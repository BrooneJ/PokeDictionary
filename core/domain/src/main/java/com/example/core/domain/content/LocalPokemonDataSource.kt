package com.example.core.domain.content

import com.example.core.domain.util.DataError
import com.example.core.domain.util.Result
import com.example.core.model.Pokemon
import com.example.core.model.PokemonDetails

interface LocalPokemonDataSource {
  suspend fun insertPokemon(pokemonList: List<Pokemon>)
  suspend fun fetchPokemonList(page: Int): Result<List<Pokemon>, DataError.Local>
  suspend fun getAllPokemonList(page: Int): Result<List<Pokemon>, DataError.Local>

  suspend fun insertPokemonDetails(pokemonDetails: PokemonDetails)
  suspend fun fetchPokemonDetails(name: String): Result<PokemonDetails, DataError.Local>
}