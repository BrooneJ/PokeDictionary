package com.example.content.presentation.libs

import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.kmpalette.palette.graphics.Palette

@Composable
fun PokemonImage(
  imageUrl: String,
  modifier: Modifier = Modifier,
  onPaletteLoaded: (Palette) -> Unit,
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
      when (painter.state) {
        AsyncImagePainter.State.Empty -> Unit
        is AsyncImagePainter.State.Error -> {
          Text("Error occurred")
        }

        is AsyncImagePainter.State.Loading -> {
          Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
          ) {
            CircularProgressIndicator()
          }
        }

        is AsyncImagePainter.State.Success -> {
          Image(
            painter = painter,
            contentDescription = null
          )
        }
      }
    },
    modifier = if (sizeResolver is ConstraintsSizeResolver) {
      modifier.then(sizeResolver)
    } else {
      modifier
    },
    measurePolicy = { measurable, constraints ->
      val placeable = measurable.first().measure(constraints)
      layout(placeable.width, placeable.height) {
        placeable.placeRelative(0, 0)
      }
    }
  )
}