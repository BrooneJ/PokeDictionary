package com.example.core.database

import com.example.core.database.dao.SampleDao
import com.example.core.domain.content.LocalSampleDataSource
import com.example.core.domain.content.Sample

class RoomLocalSampleDataSource(
  private val smapleDao: SampleDao
) : LocalSampleDataSource {

  override suspend fun upsertSample(sample: Sample) {
    TODO("Not yet implemented")
  }
}