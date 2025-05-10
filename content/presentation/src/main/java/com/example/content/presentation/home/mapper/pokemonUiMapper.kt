package com.example.content.presentation.home.mapper

import com.example.content.presentation.home.model.PokemonUi
import com.example.core.model.Pokemon

fun Pokemon.toPokemonUi(): PokemonUi {
  return PokemonUi(
    page = page,
    nameField = nameField,
    url = url,
  )
}