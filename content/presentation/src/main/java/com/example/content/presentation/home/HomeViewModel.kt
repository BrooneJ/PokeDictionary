@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.content.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.content.PokeRepository
import com.example.core.domain.content.Pokemon
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
  private val repository: PokeRepository,
) : ViewModel() {

  val uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)

  private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)

  val pokemonList: StateFlow<List<Pokemon>> = pokemonFetchingIndex.flatMapLatest { page ->
    repository.fetchPokemons(page)
      .onStart { uiState.value = HomeUiState.Loading }
      .onCompletion { uiState.value = HomeUiState.Idle }
      .catch { exception -> uiState.value = HomeUiState.Error(exception.message) }
  }.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue = emptyList(),
  )

  fun onAction(action: HomeAction) {
    when (action) {
      HomeAction.FetchPokemons -> fetchPokemon()
      else -> Unit
    }
  }

  private fun fetchPokemon() {
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