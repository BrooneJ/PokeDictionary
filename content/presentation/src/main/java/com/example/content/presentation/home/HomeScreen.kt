package com.example.content.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.content.presentation.home.component.PokemonListItem
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
  Box {
    LazyColumn {
      items(
        items = state.pokemonList,
      ) {
        PokemonListItem(
          pokemonUi = it,
          modifier = Modifier.animateItem()
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
        )
      ),
    )
  }
}