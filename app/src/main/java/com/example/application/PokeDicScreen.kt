package com.example.application

import kotlinx.serialization.Serializable

sealed interface PokeDicScreen {
  @Serializable
  data object Home : PokeDicScreen

  @Serializable
  data object Base : PokeDicScreen

  @Serializable
  data class Details(val name: String) : PokeDicScreen
}