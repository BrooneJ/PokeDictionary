package com.example.content.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.content.presentation.home.model.PokemonUi
import com.example.core.domain.content.PokeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
  private val repository: PokeRepository,
) : ViewModel() {

  var state by mutableStateOf(HomeState())
    private set

  private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)

  init {
    fetchPokemon()
  }

  fun fetchPokemon() {
    viewModelScope.launch {
      val result = repository.fetchPokemons(page = pokemonFetchingIndex.value)
      state = state.copy(
        pokemonList = state.pokemonList + result.map { pokemon ->
          PokemonUi(
            page = pokemon.page,
            nameField = pokemon.nameField,
            url = pokemon.url,
          )
        },
      )
      pokemonFetchingIndex.value += 1
    }
  }
}