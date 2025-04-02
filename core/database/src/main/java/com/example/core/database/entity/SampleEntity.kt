package com.example.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SampleEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Int,
  val name: String
)
