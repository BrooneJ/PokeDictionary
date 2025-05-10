package com.example.content.network

import com.example.content.network.mappers.toPokemonDetails
import com.example.content.network.mappers.toPokemonList
import com.example.core.data.networking.get
import com.example.core.domain.content.RemotePokemonDataSource
import com.example.core.domain.util.DataError
import com.example.core.domain.util.Result
import com.example.core.domain.util.map
import com.example.core.model.Pokemon
import com.example.core.model.PokemonDetails
import io.ktor.client.HttpClient

class KtorRemotePokemonDataSource(
  private val httpClient: HttpClient
) : RemotePokemonDataSource {
  override suspend fun getPokemonList(page: Int): Result<List<Pokemon>, DataError.Network> {
    val result = httpClient.get<PokemonResponseDto>(
      route = "/pokemon",
      queryParameters = mapOf(
        "limit" to PAGING_SIZE,
        "offset" to (PAGING_SIZE * page),
      )
    )

    return result.map { it.toPokemonList() }
  }

  override suspend fun getPokemonByName(name: String): Result<PokemonDetails, DataError.Network> {
    val result = httpClient.get<PokemonDetailsDto>(
      route = "/pokemon/$name",
    )

    return result.map { it.toPokemonDetails() }
  }

  companion object {
    private const val PAGING_SIZE = 20
  }
}