package com.example.content.network

import com.example.core.domain.content.RemoteSampleDataSource
import com.example.core.domain.content.Sample
import com.example.core.domain.util.DataError
import com.example.core.domain.util.Result
import io.ktor.client.HttpClient

class KtorRemoteSampleDataSource(
  private val httpClient: HttpClient
) : RemoteSampleDataSource {
  override suspend fun getSamples(): Result<List<Sample>, DataError.Network> {
    TODO("Not yet implemented")
    /*
    return httpClient.get<List<SampleDto>>(
      route = "samples"
    ).map { sampleDtos ->
      sampleDtos.map {  }
    }
     */
  }

  override suspend fun getSampleById(id: Int): Result<Sample, DataError.Network> {
    TODO("Not yet implemented")
  }

  override suspend fun postSample(sample: Sample): Result<Unit, DataError.Network> {
    TODO("Not yet implemented")
    /*
    val createSampleRequestJson = Json.encodeToString(sample.toCreateSampleRequest())
    val result = safeCall<SampleDto> {
      httpClient.submitForm(
        url = constructRoute("/samples"),
      )
    }

    return result.map { }
     */
  }

  override suspend fun deleteSample(id: Int): Result<Unit, DataError.Network> {
    TODO("Not yet implemented")
  }
}