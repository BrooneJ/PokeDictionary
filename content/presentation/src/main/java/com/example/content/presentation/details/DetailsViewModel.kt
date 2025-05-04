package com.example.content.presentation.details

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.content.PokeRepository
import com.example.core.model.Pokemon
import com.example.core.model.PokemonDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class DetailsViewModel(
  private val repository: PokeRepository,
  savedStateHandle: SavedStateHandle,
) : ViewModel() {

  val pokemon = checkNotNull(savedStateHandle.get<Pokemon>("pokemon"))

  var state by mutableStateOf(DetailsState())
    private set
  val uiState: MutableStateFlow<DetailsUiState> = MutableStateFlow(DetailsUiState.Loading)

  init {
    viewModelScope.launch {
      val pokemonInfo = fetchDetails(pokemon.nameField.replaceFirstChar { it.lowercase() })
      Timber.d("fetchDetails: $pokemonInfo")
    }
  }

  private suspend fun fetchDetails(name: String): PokemonDetails? {
    return repository.fetchPokemonDetails(name)
  }
}

@Stable
sealed interface DetailsUiState {
  data object Idle : DetailsUiState
  data object Loading : DetailsUiState
  data class Error(val message: String?) : DetailsUiState
}