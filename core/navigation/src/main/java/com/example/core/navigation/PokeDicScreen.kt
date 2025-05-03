package com.example.core.navigation

import com.example.core.domain.content.Pokemon
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

sealed interface PokeDicScreen {
  @Serializable
  data object Home : PokeDicScreen

  @Serializable
  data object Base : PokeDicScreen

  @Serializable
  data class Details(val pokemon: Pokemon) : PokeDicScreen {
    companion object {
      val typeMap = mapOf(
        typeOf<Pokemon>() to PokemonType
      )
    }
  }
}