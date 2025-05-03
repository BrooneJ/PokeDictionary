package com.example.content.presentation.details

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.core.domain.content.PokeRepository
import com.example.core.model.PokemonDetails
import kotlinx.coroutines.flow.MutableStateFlow

class DetailsViewModel(
  private val repository: PokeRepository,
  savedStateHandle: SavedStateHandle,
) : ViewModel() {

  var state by mutableStateOf(DetailsState())
    private set
  val uiState: MutableStateFlow<DetailsUiState> = MutableStateFlow(DetailsUiState.Loading)

  init {
//    viewModelScope.launch {
//      val pokemonInfo = fetchDetails()
//      _pokemon.value = pokemonInfo
//    }
  }

  private suspend fun fetchDetails(): PokemonDetails? {
    return repository.fetchPokemonDetails("1")
  }
}

@Stable
sealed interface DetailsUiState {
  data object Idle : DetailsUiState
  data object Loading : DetailsUiState
  data class Error(val message: String?) : DetailsUiState
}