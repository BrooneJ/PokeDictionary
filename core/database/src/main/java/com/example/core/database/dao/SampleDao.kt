package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.example.core.database.entity.SampleEntity

@Dao
interface SampleDao {

  @Upsert
  suspend fun upsertSample(sample: SampleEntity)
}