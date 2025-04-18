package com.example.content.presentation.home

sealed interface HomeAction {
  data object FetchPokemon : HomeAction
}