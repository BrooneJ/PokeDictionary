package com.example.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
  @PrimaryKey val name: String,
  var page: Int = 0,
  val url: String,
)
