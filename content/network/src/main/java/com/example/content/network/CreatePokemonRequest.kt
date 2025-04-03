package com.example.content.network

import kotlinx.serialization.Serializable

@Serializable
data class CreatePokemonRequest(
  val id: Int,
  val name: String,
)