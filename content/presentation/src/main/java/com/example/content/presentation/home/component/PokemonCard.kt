package com.example.content.presentation.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.content.presentation.home.model.PokemonUi
import com.example.content.presentation.libs.PokemonImage
import com.example.core.presentation.designsystem.JetpackApplicationTheme
import com.kmpalette.palette.graphics.Palette

@Composable
fun PokemonCard(
  backgroundColor: Color,
  pokemonUi: PokemonUi,
  modifier: Modifier = Modifier,
  onPaletteLoaded: (Palette) -> Unit = {},
  onClick: () -> Unit = {},
) {
  Card(
    modifier = modifier
      .padding(4.dp)
      .clickable { onClick() },
    shape = RoundedCornerShape(15.dp),
    colors = CardColors(
      containerColor = backgroundColor,
      contentColor = backgroundColor,
      disabledContentColor = backgroundColor,
      disabledContainerColor = backgroundColor
    ),
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
  ) {
    Column {
      PokemonImage(
        imageUrl = pokemonUi.imageUrl,
        modifier = Modifier,
        onPaletteLoaded = { palette ->
          onPaletteLoaded(palette)
        }
      )
      Text(
        text = pokemonUi.nameField,
        color = MaterialTheme.colorScheme.onSurface
      )
    }
  }
}

@Preview
@Composable
private fun PokemonListItemPreview() {
  JetpackApplicationTheme {
    PokemonCard(
      backgroundColor = MaterialTheme.colorScheme.background,
      pokemonUi = PokemonUi(
        page = 0,
        nameField = "Pikachu",
        url = "https://pokeapi.co/api/v2/pokemon/1/",
      ),
    )
  }
}