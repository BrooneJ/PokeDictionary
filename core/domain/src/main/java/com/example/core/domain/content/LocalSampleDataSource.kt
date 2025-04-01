package com.example.core.domain.content

interface LocalSampleDataSource {
  suspend fun upsertSample(sample: Sample)
}