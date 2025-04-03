package com.example.content.network

import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponseDto(
  val count: Int,
  val next: String?,
  val previous: String?,
  val results: List<PokemonDto>,
)