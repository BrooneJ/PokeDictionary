package com.example.content.presentation.details

import androidx.lifecycle.ViewModel
import com.example.core.domain.content.PokeRepository

class DetailsViewModel(
  private val repository: PokeRepository
) : ViewModel() {

  val state: DetailsState = DetailsState()

  fun onAction(action: DetailsAction) {
    when (action) {
      DetailsAction.FetchDetails -> fetchDetails()
    }
  }

  private fun fetchDetails() {
    repository.fetchPokemonDetails()
  }
}