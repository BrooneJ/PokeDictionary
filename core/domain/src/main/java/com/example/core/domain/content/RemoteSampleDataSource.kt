package com.example.core.domain.content

import com.example.core.domain.util.DataError
import com.example.core.domain.util.Result

interface RemoteSampleDataSource {
  suspend fun getSamples(): Result<List<Sample>, DataError.Network>
  suspend fun getSampleById(id: Int): Result<Sample, DataError.Network>
  suspend fun postSample(sample: Sample): Result<Unit, DataError.Network>
  suspend fun deleteSample(id: Int): Result<Unit, DataError.Network>
}