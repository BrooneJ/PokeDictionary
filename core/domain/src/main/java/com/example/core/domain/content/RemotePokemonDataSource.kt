package com.example.core.domain.content

import com.example.core.domain.util.DataError
import com.example.core.domain.util.Result
import com.example.core.model.Pokemon
import com.example.core.model.PokemonDetails

interface RemotePokemonDataSource {
  suspend fun getPokemonList(page: Int): Result<List<Pokemon>, DataError.Network>
  suspend fun getPokemonByName(name: String): Result<PokemonDetails, DataError.Network>
}