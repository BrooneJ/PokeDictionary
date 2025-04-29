package com.example.content.presentation.details

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.content.PokeRepository
import com.example.core.domain.content.PokemonDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class DetailsViewModel(
  private val repository: PokeRepository,
  savedStateHandle: SavedStateHandle,
) : ViewModel() {

  private val pokemonName: String = checkNotNull(savedStateHandle.get<String>("name"))

  private val _pokemon = MutableStateFlow<PokemonDetails?>(null)
  val pokemon = _pokemon.asStateFlow()

  var state by mutableStateOf(DetailsState())
    private set
  val uiState: MutableStateFlow<DetailsUiState> = MutableStateFlow(DetailsUiState.Loading)
//  val pokemon = savedStateHandle.getStateFlow<Pokemon?>("pokemon", null)

  init {
    viewModelScope.launch {
      Timber.d("Pokemon name: $pokemonName")
      val pokemonInfo = repository.fetchPokemonDetails(pokemonName)
      _pokemon.value = pokemonInfo
    }
  }

  fun onAction(action: DetailsAction) {
    when (action) {
      DetailsAction.FetchDetails -> {
        viewModelScope.launch {
          uiState.value = DetailsUiState.Loading
          try {
            val pokemonDetailsResult = fetchDetails()
            Timber.d("Pokemon details: $pokemonDetailsResult")
            state = state.copy(
              state = pokemonDetailsResult
            )
            uiState.value = DetailsUiState.Idle
          } catch (e: Exception) {
            uiState.value = DetailsUiState.Error(e.message)
          }
        }
      }
    }
  }

  private suspend fun fetchDetails(): PokemonDetails? {
    return repository.fetchPokemonDetails(pokemon.value?.name ?: "")
  }
}

@Stable
sealed interface DetailsUiState {
  data object Idle : DetailsUiState
  data object Loading : DetailsUiState
  data class Error(val message: String?) : DetailsUiState
}