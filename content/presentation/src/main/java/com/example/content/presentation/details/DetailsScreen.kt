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
import com.example.core.domain.content.Pokemon
import com.example.core.presentation.designsystem.JetpackApplicationTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreenRoot(
  pokemon: Pokemon,
  viewModel: DetailsViewModel = koinViewModel()
) {
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
  pokemon: Pokemon
) {

  Box(
    modifier = Modifier
      .fillMaxWidth(),
    contentAlignment = Alignment.Center,
  ) {
    Text(pokemon.nameField)
  }
}

@Preview
@Composable
private fun DetailsScreenPreview() {
  JetpackApplicationTheme {
    DetailsScreen(
      state = DetailsState(),
      uiState = DetailsUiState.Loading,
      pokemon = Pokemon(
        page = 0,
        nameField = "Pikachu",
        url = "https://pokeapi.co/api/v2/pokemon/1/"
      )
    )
  }
}