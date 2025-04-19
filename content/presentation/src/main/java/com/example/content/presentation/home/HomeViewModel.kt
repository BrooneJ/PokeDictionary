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
  val uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)

  private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)

  init {
    fetchPokemon()
  }

  fun onAction(action: HomeAction) {
    when (action) {
      HomeAction.FetchPokemon -> fetchPokemon()
    }
  }

  private fun fetchPokemon() {
    uiState.value = HomeUiState.Loading
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
    }
    uiState.value = HomeUiState.Idle
    if (uiState.value != HomeUiState.Loading) {
      pokemonFetchingIndex.value++
    }
  }
}

sealed interface HomeUiState {
  data object Idle : HomeUiState
  data object Loading : HomeUiState
  data class Error(val message: String?) : HomeUiState
}