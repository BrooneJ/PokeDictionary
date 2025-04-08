package com.example.content.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.content.presentation.R
import com.example.content.presentation.home.model.PokemonUi
import com.example.core.presentation.designsystem.JetpackApplicationTheme
import com.kmpalette.loader.rememberNetworkLoader
import com.kmpalette.palette.graphics.Palette
import io.ktor.http.Url

@Composable
fun PokemonListItem(
  backgroundColor: Color,
  pokemonUi: PokemonUi,
  onPaletteLoaded: (Palette) -> Unit = {},
  modifier: Modifier = Modifier,
) {
  val networkLoader = rememberNetworkLoader()
  val paletteState = com.kmpalette.rememberPaletteState(loader = networkLoader, cacheSize = 6)
  val imageStringToUrl = Url(pokemonUi.imageUrl)
  LaunchedEffect(pokemonUi.imageUrl) {
    paletteState.generate(imageStringToUrl)
    val palette = paletteState.palette

    if (palette != null) {
      onPaletteLoaded(palette)
    }
  }
  Card(
    modifier = Modifier
      .padding(6.dp)
      .fillMaxWidth(),
    shape = RoundedCornerShape(15.dp),
    colors = CardColors(
      containerColor = backgroundColor,
      contentColor = backgroundColor,
      disabledContentColor = backgroundColor,
      disabledContainerColor = backgroundColor
    ),
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
  ) {
    PokemonImage(imageUrl = pokemonUi.imageUrl)
    Text(
      text = pokemonUi.nameField,
      color = MaterialTheme.colorScheme.onSurface
    )
  }
}

@Composable
private fun PokemonImage(
  imageUrl: String,
  modifier: Modifier = Modifier
) {
  SubcomposeAsyncImage(
    model = imageUrl,
    contentDescription = stringResource(id = R.string.pokomon_image),
    modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(15.dp)),
    loading = {
      Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
      ) {
        CircularProgressIndicator(
          modifier = Modifier.size(20.dp),
          strokeWidth = 2.dp,
          color = MaterialTheme.colorScheme.onSurface
        )
      }
    },
    error = {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(MaterialTheme.colorScheme.errorContainer),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = stringResource(id = R.string.could_not_load_image),
          color = MaterialTheme.colorScheme.onError
        )
      }
    }
  )
}

@Preview
@Composable
private fun PokemonListItemPreview() {
  JetpackApplicationTheme {
    PokemonListItem(
      backgroundColor = MaterialTheme.colorScheme.background,
      pokemonUi = PokemonUi(
        page = 0,
        nameField = "Pikachu",
        url = "https://pokeapi.co/api/v2/pokemon/1/",
      ),
    )
  }
}