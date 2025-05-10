package com.example.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.model.PokemonDetails

@Entity
data class PokemonDetailsEntity(
  @PrimaryKey val id: Int,
  val name: String,
  val height: Int,
  val weight: Int,
  val experience: Int,
  val types: List<PokemonDetails.TypeResponse>,
  val stats: List<PokemonDetails.StatsResponse>,
  val exp: Int
)
