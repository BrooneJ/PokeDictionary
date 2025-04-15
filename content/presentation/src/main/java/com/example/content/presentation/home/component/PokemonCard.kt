package com.example.content.presentation.home.component

import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
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
import com.kmpalette.palette.graphics.Palette

@Composable
fun PokemonCard(
  backgroundColor: Color,
  pokemonUi: PokemonUi,
  modifier: Modifier = Modifier,
  onPaletteLoaded: (Palette) -> Unit = {},
) {
  Card(
    modifier = modifier
      .padding(4.dp),
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

@Composable
private fun PokemonImage(
  imageUrl: String,
  modifier: Modifier = Modifier,
  onPaletteLoaded: (Palette) -> Unit = {},
) {

  val request = requestOfWithSizeResolver(
    model = imageUrl,
    contentScale = ContentScale.Fit
  )

  val sizeResolver = request.sizeResolver

  val painter = rememberAsyncImagePainter(model = request)

  val state = painter.state
  if (state is AsyncImagePainter.State.Success) {
    val drawable = state.result.drawable
    if (drawable is BitmapDrawable) {
      val bitmap = drawable.bitmap
      val imageBitmap = bitmap.asImageBitmap()
      LaunchedEffect(imageBitmap) {
        val palette = Palette.from(imageBitmap).generate()
        onPaletteLoaded(palette)
      }
    }
  }

  Layout(
    content = {
      Image(
        painter = painter,
        contentDescription = null,
      )
    },
    modifier = if (sizeResolver is ConstraintsSizeResolver) {
      modifier.then(sizeResolver)
    } else {
      modifier
    },
    measurePolicy = { measurable, constraints ->
      val placeable = measurable.first().measure(constraints)
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