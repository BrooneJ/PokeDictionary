package com.example.content.presentation.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.domain.content.PokemonDetails
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
    onAction = viewModel::onAction,
    pokemon = pokemon
  )
}

@Composable
private fun DetailsScreen(
  state: DetailsState,
  uiState: DetailsUiState,
  onAction: (DetailsAction) -> Unit,
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

//@Preview
//@Composable
//private fun DetailsScreenPreview() {
//  JetpackApplicationTheme {
//    DetailsScreen(
//      state = DetailsState(),
//      uiState = DetailsUiState.Loading,
//      onAction = {},
//      pokemon = Pokemon(
//        page = 0,
//        nameField = "pikachu",
//        url = "https://pokeapi.co/api/v2/pokemon/25/"
//      )
//    )
//  }
//}