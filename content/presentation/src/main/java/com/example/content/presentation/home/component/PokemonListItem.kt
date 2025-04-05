package com.example.content.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.content.presentation.home.model.PokemonUi
import com.example.core.presentation.designsystem.JetpackApplicationTheme

@Composable
fun PokemonListItem(
  pokemonUi: PokemonUi,
  modifier: Modifier = Modifier,
) {
  Box {
    Column(
      modifier = modifier
        .clip(RoundedCornerShape(15.dp))
        .background(MaterialTheme.colorScheme.surface)
    ) {
      Box {
        Text(
          text = pokemonUi.nameField,
          color = MaterialTheme.colorScheme.onSurface
        )
      }
    }
  }
}

@Preview
@Composable
private fun PokemonListItemPreview() {
  JetpackApplicationTheme {
    PokemonListItem(
      pokemonUi = PokemonUi(
        page = 0,
        nameField = "Pikachu",
        url = "https://pokeapi.co/api/v2/pokemon/1/",
      ),
    )
  }
}