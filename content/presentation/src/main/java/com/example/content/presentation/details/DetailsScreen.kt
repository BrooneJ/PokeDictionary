package com.example.content.presentation.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.domain.content.PokemonDetails
import com.example.core.presentation.designsystem.JetpackApplicationTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreenRoot(
  viewModel: DetailsViewModel = koinViewModel()
) {
  val pokemon by viewModel.pokemon.collectAsStateWithLifecycle()
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

  DetailsScreen(
    state = viewModel.state,
    uiState = uiState,
    pokemon = pokemon
  )
}

@Composable
private fun DetailsScreen(
  state: DetailsState,
  uiState: DetailsUiState,
  pokemon: PokemonDetails?
) {
  Box(
    modifier = Modifier
      .fillMaxWidth(),
    contentAlignment = Alignment.Center,
  ) {
    Text("${pokemon?.name}")
  }
}

@Preview
@Composable
private fun DetailsScreenPreview() {
  JetpackApplicationTheme {
    DetailsScreen(
      state = DetailsState(),
      uiState = DetailsUiState.Loading,
      pokemon = PokemonDetails(
        id = 1,
        name = "Pikachu",
        height = 123,
        weight = 123,
        experience = 33,
        types = listOf(),
        stats = listOf(),
        exp = 123,
      )
    )
  }
}