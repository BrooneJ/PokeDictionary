package com.example.content.presentation.details

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import com.example.core.domain.content.PokeRepository
import kotlinx.coroutines.flow.MutableStateFlow

class DetailsViewModel(
  private val repository: PokeRepository
) : ViewModel() {

  val state: DetailsState = DetailsState()
  val uiState: MutableStateFlow<DetailsUiState> = MutableStateFlow(DetailsUiState.Loading)

  fun onAction(action: DetailsAction) {
    when (action) {
      DetailsAction.FetchDetails -> fetchDetails()
    }
  }

  private fun fetchDetails() {
    repository.fetchPokemonDetails()
  }
}

@Stable
sealed interface DetailsUiState {
  data object Idle : DetailsUiState
  data object Loading : DetailsUiState
  data class Error(val message: String?) : DetailsUiState
}