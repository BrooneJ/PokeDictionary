package com.example.content.network

import com.example.core.data.networking.get
import com.example.core.domain.content.Pokemon
import com.example.core.domain.content.RemotePokemonDataSource
import com.example.core.domain.util.DataError
import com.example.core.domain.util.Result
import com.example.core.domain.util.map
import io.ktor.client.HttpClient

class KtorRemotePokemonDataSource(
  private val httpClient: HttpClient
) : RemotePokemonDataSource {
  override suspend fun getPokemonList(page: Int): Result<List<Pokemon>, DataError.Network> {
    val result = httpClient.get<PokemonResponseDto>(
      route = "/",
      queryParameters = mapOf(
        "limit" to PAGING_SIZE,
        "offset" to (PAGING_SIZE * page),
      )
    )

    return result.map { pokemonResponseDto ->
      pokemonResponseDto.toPokemonList()
    }
  }

  override suspend fun getPokemonById(id: Int): Result<Pokemon, DataError.Network> {
    TODO("Not yet implemented")
  }

  companion object {
    private const val PAGING_SIZE = 20
  }
}