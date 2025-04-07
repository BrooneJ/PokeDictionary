package com.example.content.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.content.presentation.home.component.PokemonListItem
import com.example.content.presentation.home.component.paletteBackgroundColor
import com.example.content.presentation.home.lib.rememberPaletteState
import com.example.content.presentation.home.model.PokemonUi
import com.example.core.presentation.designsystem.JetpackApplicationTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreenRoot(
  viewModel: HomeViewModel = koinViewModel()
) {
  HomeScreen(
    state = viewModel.state,
  )
}

@Composable
private fun HomeScreen(
  state: HomeState,
) {
  Box(modifier = Modifier.fillMaxSize()) {
    LazyVerticalGrid(
      columns = GridCells.Fixed(2),
      contentPadding = PaddingValues(6.dp),
    ) {
      items(state.pokemonList) {

        var palette by rememberPaletteState()
        val backgroundColor by palette.paletteBackgroundColor()

        PokemonListItem(
          backgroundColor = backgroundColor,
          pokemonUi = it,
          modifier = Modifier
            .padding(4.dp)
            .animateItem(),
          onPaletteLoaded = { palette = it },
        )
      }
    }
  }
}

@Preview
@Composable
private fun HomeScreenPreview() {
  JetpackApplicationTheme {
    HomeScreen(
      state = HomeState(
        pokemonList = listOf(
          PokemonUi(
            page = 0,
            nameField = "Pikachu",
            url = "https://pokeapi.co/api/v2/pokemon/1/",
          ),
          PokemonUi(
            page = 1,
            nameField = "Bulbasaur",
            url = "https://pokeapi.co/api/v2/pokemon/2/",
          ),
          PokemonUi(
            page = 2,
            nameField = "Charmander",
            url = "https://pokeapi.co/api/v2/pokemon/3/",
          ),
          PokemonUi(
            page = 3,
            nameField = "Squirtle",
            url = "https://pokeapi.co/api/v2/pokemon/4/",
          ),
        )
      ),
    )
  }
}