package com.example.content.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.content.presentation.home.component.PokemonCard
import com.example.content.presentation.home.component.paletteBackgroundColor
import com.example.content.presentation.home.libs.rememberPaletteState
import com.example.content.presentation.home.mapper.toPokemonUi
import com.example.core.model.Pokemon
import com.example.core.presentation.designsystem.JetpackApplicationTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreenRoot(
  viewModel: HomeViewModel = koinViewModel(),
  onPokemonClick: (Pokemon) -> Unit
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val pokemonList by viewModel.pokemonList.collectAsStateWithLifecycle()

  HomeScreen(
    pokemonList = pokemonList,
    uiState = uiState,
    onAction = { action ->
      when (action) {
        HomeAction.FetchPokemons -> viewModel.onAction(action)
        is HomeAction.OnPokemonClick -> onPokemonClick(action.pokemon)
      }
    }
  )
}

@Composable
private fun HomeScreen(
  pokemonList: List<Pokemon>,
  uiState: HomeUiState,
  onAction: (HomeAction) -> Unit,
) {
  
  Box(
    modifier = Modifier
      .fillMaxSize()
      .statusBarsPadding()
  ) {
    val threshold = 8
    LazyVerticalGrid(
      columns = GridCells.Fixed(2),
      contentPadding = PaddingValues(6.dp),
    ) {
      itemsIndexed(
        items = pokemonList,
        key = { _, pokemon -> pokemon.name }
      ) { index, pokemon ->
        if ((index + threshold) >= pokemonList.size && uiState != HomeUiState.Loading) {
          onAction(HomeAction.FetchPokemons)
        }

        var palette by rememberPaletteState()
        val backgroundColor by palette.paletteBackgroundColor()

        PokemonCard(
          backgroundColor = backgroundColor,
          pokemonUi = pokemon.toPokemonUi(),
          modifier = Modifier,
          onPaletteLoaded = { newPalette ->
            palette = newPalette
          },
          onClick = { onAction(HomeAction.OnPokemonClick(pokemon)) }
        )
      }
    }
  }
}

@Preview
@Composable
fun HomeScreenPreview() {
  JetpackApplicationTheme {
    HomeScreen(
      pokemonList = listOf(
        Pokemon(
          page = 0,
          nameField = "Pikachu",
          url = "https://pokeapi.co/api/v2/pokemon/1/",
        ),
        Pokemon(
          page = 1,
          nameField = "Bulbasaur",
          url = "https://pokeapi.co/api/v2/pokemon/2/",
        ),
        Pokemon(
          page = 2,
          nameField = "Charmander",
          url = "https://pokeapi.co/api/v2/pokemon/3/",
        ),
        Pokemon(
          page = 3,
          nameField = "Squirtle",
          url = "https://pokeapi.co/api/v2/pokemon/4/",
        ),
      ),
      uiState = HomeUiState.Idle,
      onAction = {}
    )
  }
}