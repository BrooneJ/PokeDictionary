package com.example.content.network

import com.example.core.domain.content.RemotePokemonDataSource
import com.example.core.domain.content.Pokemon
import com.example.core.domain.util.DataError
import com.example.core.domain.util.Result
import io.ktor.client.HttpClient

class KtorRemotePokemonDataSource(
  private val httpClient: HttpClient
) : RemotePokemonDataSource {
  override suspend fun getPokemons(): Result<List<Pokemon>, DataError.Network> {
    TODO("Not yet implemented")
    /*
    return httpClient.get<List<SampleDto>>(
      route = "samples"
    ).map { sampleDtos ->
      sampleDtos.map {  }
    }
     */
  }

  override suspend fun getPokemonById(id: Int): Result<Pokemon, DataError.Network> {
    TODO("Not yet implemented")
  }

}