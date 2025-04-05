package com.example.content.presentation.home.mapper

import com.example.content.presentation.home.model.PokemonUi
import com.example.core.domain.content.Pokemon

fun Pokemon.toPokemonUi(): PokemonUi {
    return PokemonUi(
        nameField = nameField,
        url = url,
        page = page
    )
}