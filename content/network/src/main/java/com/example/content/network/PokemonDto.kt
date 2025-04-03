package com.example.content.network

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class PokemonDto(
  var page: Int = 0,
  val nameField: String,
  val url: String,
) {

  val name: String
    get() = nameField.replaceFirstChar { it.uppercase() }
}