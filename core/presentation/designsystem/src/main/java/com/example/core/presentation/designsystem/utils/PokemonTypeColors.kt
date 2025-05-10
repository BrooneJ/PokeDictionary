package com.example.core.presentation.designsystem.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.core.presentation.designsystem.PokeDicTheme

@Composable
fun getPokemonTypeColor(type: String): Color {
  return when (type) {
    "fighting" -> PokeDicTheme.colors.fighting
    "flying" -> PokeDicTheme.colors.flying
    "poison" -> PokeDicTheme.colors.poison
    "ground" -> PokeDicTheme.colors.ground
    "rock" -> PokeDicTheme.colors.rock
    "bug" -> PokeDicTheme.colors.bug
    "ghost" -> PokeDicTheme.colors.ghost
    "steel" -> PokeDicTheme.colors.steel
    "fire" -> PokeDicTheme.colors.fire
    "water" -> PokeDicTheme.colors.water
    "grass" -> PokeDicTheme.colors.grass
    "electric" -> PokeDicTheme.colors.electric
    "psychic" -> PokeDicTheme.colors.psychic
    "ice" -> PokeDicTheme.colors.ice
    "dragon" -> PokeDicTheme.colors.dragon
    "fairy" -> PokeDicTheme.colors.fairy
    "dark" -> PokeDicTheme.colors.dark
    else -> PokeDicTheme.colors.gray21
  }
}