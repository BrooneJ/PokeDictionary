package com.example.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.database.dao.SampleDao
import com.example.core.database.entity.SampleEntity

@Database(
  entities = [SampleEntity::class],
  version = 1
)
abstract class SampleDatabase : RoomDatabase() {
  
  abstract fun sampleDao(): SampleDao
}