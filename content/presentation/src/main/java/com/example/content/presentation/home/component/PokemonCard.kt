package com.example.content.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.content.presentation.home.libs.ConstraintsSizeResolver
import com.example.content.presentation.home.libs.requestOfWithSizeResolver
import com.example.content.presentation.home.model.PokemonUi
import com.example.core.presentation.designsystem.JetpackApplicationTheme
import com.kmpalette.loader.rememberNetworkLoader
import com.kmpalette.palette.graphics.Palette
import io.ktor.http.Url
import timber.log.Timber

@Composable
fun PokemonCard(
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
    modifier = modifier,
    shape = RoundedCornerShape(15.dp),
    colors = CardColors(
      containerColor = backgroundColor,
      contentColor = backgroundColor,
      disabledContentColor = backgroundColor,
      disabledContainerColor = backgroundColor
    ),
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
    ) {
      PokemonImage(
        imageUrl = pokemonUi.imageUrl,
        modifier = Modifier
          .fillMaxWidth()
      )
      Text(
        text = pokemonUi.nameField,
        color = MaterialTheme.colorScheme.onSurface
      )
    }
  }
}

@Composable
private fun PokemonImage(
  imageUrl: String,
  modifier: Modifier = Modifier
) {
  val constraintsSizeResolver = remember { ConstraintsSizeResolver() }

  Layout(
    content = {
      val request = requestOfWithSizeResolver(
        model = imageUrl,
        sizeResolver = constraintsSizeResolver,
        contentScale = ContentScale.Fit
      )
      val painter = rememberAsyncImagePainter(
        model = request,
        onState = { state ->
          when (state) {
            is AsyncImagePainter.State.Loading -> Timber.tag("PokemonImage")
              .d("Loading image: $imageUrl")

            is AsyncImagePainter.State.Success -> Timber.tag("PokemonImage")
              .d("Successfully loaded image: $imageUrl")

            is AsyncImagePainter.State.Error -> Timber.tag("PokemonImage")
              .e(state.result.throwable, "Error loading image: $imageUrl")

            else -> Timber.tag("PokemonImage").d("Other state: $state")
          }
        }
      )
      Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Fit
      )
    },
    modifier = modifier.then(constraintsSizeResolver),
    measurePolicy = { measurables, constraints ->
      val placeable = measurables.first().measure(constraints)
      layout(placeable.width, placeable.height) {
        placeable.place(0, 0)
      }
    }
  )
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