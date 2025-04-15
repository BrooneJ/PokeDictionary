package com.example.content.presentation.home.libs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.request.ImageRequest
import coil.size.Size
import coil.size.SizeResolver

@Composable
@NonRestartableComposable
fun requestOfWithSizeResolver(
  model: Any?,
  contentScale: ContentScale,
): ImageRequest {
  if (model is ImageRequest && model.defined.sizeResolver != null) {
    return model
  }

  val originalSizeResolver = SizeResolver(Size.ORIGINAL)
  val sizeResolver = if (contentScale == ContentScale.None) {
    originalSizeResolver
  } else {
    remember { ConstraintsSizeResolver() }
  }

  if (model is ImageRequest) {
    return remember(model, sizeResolver) {
      model.newBuilder()
        .size(sizeResolver)
        .build()
    }
  } else {
    val context = LocalContext.current
    return remember(context, model, sizeResolver) {
      ImageRequest.Builder(context)
        .data(model)
        .size(sizeResolver)
        .build()
    }
  }
}