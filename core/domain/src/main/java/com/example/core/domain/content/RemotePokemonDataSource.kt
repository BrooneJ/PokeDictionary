package com.example.core.domain.content

import com.example.core.domain.util.DataError
import com.example.core.domain.util.Result

interface RemotePokemonDataSource {
  suspend fun getPokemons(): Result<List<Pokemon>, DataError.Network>
  suspend fun getPokemonById(id: Int): Result<Pokemon, DataError.Network>
}