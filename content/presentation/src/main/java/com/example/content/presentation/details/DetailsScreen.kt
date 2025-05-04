package com.example.content.presentation.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.model.Pokemon
import com.example.core.model.PokemonDetails
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreenRoot(
  viewModel: DetailsViewModel = koinViewModel()
) {
  val pokemon by viewModel.pokemon.collectAsStateWithLifecycle()
  val pokemonDetails by viewModel.pokemonDetails.collectAsStateWithLifecycle()
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

  DetailsScreen(
    state = viewModel.state,
    uiState = uiState,
    pokemon = pokemon,
    pokemonDetails = pokemonDetails
  )
}

@Composable
private fun DetailsScreen(
  state: DetailsState,
  uiState: DetailsUiState,
  pokemon: Pokemon?,
  pokemonDetails: PokemonDetails?
) {

  Box(
    modifier = Modifier
      .fillMaxWidth(),
    contentAlignment = Alignment.Center,
  ) {
    Column {
      Text(pokemon?.nameField.orEmpty())
      Text(pokemonDetails?.weight.toString())
    }
  }
}

@Composable
private fun DetailsHeader(
  pokemon: Pokemon
) {
}

//@Preview
//@Composable
//private fun DetailsScreenPreview() {
//  JetpackApplicationTheme {
//    DetailsScreen(
//      state = DetailsState(),
//      uiState = DetailsUiState.Loading,
//      pokemon = Pokemon(
//        page = 0,
//        nameField = "Pikachu",
//        url = "https://pokeapi.co/api/v2/pokemon/1/"
//      )
//    )
//  }
//}