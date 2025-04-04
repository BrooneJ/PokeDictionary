package com.example.core.domain.content

import com.example.core.domain.util.DataError
import com.example.core.domain.util.Result

interface RemotePokemonDataSource {
  suspend fun getPokemonList(page: Int): Result<List<Pokemon>, DataError.Network>
  suspend fun getPokemonById(id: Int): Result<Pokemon, DataError.Network>
}