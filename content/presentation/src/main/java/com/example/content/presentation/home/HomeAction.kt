package com.example.content.presentation.home

import com.example.core.domain.content.Pokemon

sealed interface HomeAction {
  data object FetchPokemons : HomeAction
  data class OnPokemonClick(val pokemon: Pokemon) : HomeAction
}