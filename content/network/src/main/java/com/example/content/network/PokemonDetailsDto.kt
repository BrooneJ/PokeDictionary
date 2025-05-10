package com.example.content.network

import androidx.compose.runtime.Immutable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class PokemonDetailsDto(
  @SerialName(value = "id") val id: Int,
  @SerialName(value = "name") val name: String,
  @SerialName(value = "height") val height: Int,
  @SerialName(value = "weight") val weight: Int,
  @SerialName(value = "base_experience") val experience: Int,
  @SerialName(value = "types") val types: List<TypeResponse>,
  @SerialName(value = "stats") val stats: List<StatsResponse>,
) {

  @Serializable
  data class TypeResponse(
    @SerialName(value = "slot") val slot: Int,
    @SerialName(value = "type") val type: Type
  )

  @Serializable
  data class StatsResponse(
    @SerialName(value = "base_stat") val baseStat: Int,
    @SerialName(value = "effort") val effort: Int,
    @SerialName(value = "stat") val stat: Stat
  )

  @Serializable
  data class Stat(
    @SerialName(value = "name") val name: String
  )

  @Serializable
  data class Type(
    @SerialName(value = "name") val name: String
  )
}
