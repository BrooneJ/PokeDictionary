package com.example.content.presentation.home

import com.example.content.presentation.home.model.PokemonUi

data class HomeState(
    val pokemonList: List<PokemonUi> = emptyList(),
)