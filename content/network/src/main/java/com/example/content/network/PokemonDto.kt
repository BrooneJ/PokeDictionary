package com.example.content.network

import androidx.compose.runtime.Immutable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class PokemonDto(
  var page: Int = 0,
  @SerialName(value = "name")
  val nameField: String,
  @SerialName(value = "url")
  val url: String,
) {

  val name: String
    get() = nameField.replaceFirstChar { it.uppercase() }
}