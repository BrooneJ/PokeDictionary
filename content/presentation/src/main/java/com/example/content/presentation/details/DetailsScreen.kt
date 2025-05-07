package com.example.content.presentation.details

import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.AbstractApplier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.content.presentation.details.component.paletteBackgroundBrush
import com.example.content.presentation.details.libs.PokedexText
import com.example.content.presentation.details.libs.rememberPaletteState
import com.example.content.presentation.home.libs.ConstraintsSizeResolver
import com.example.content.presentation.home.libs.requestOfWithSizeResolver
import com.example.core.model.Pokemon
import com.example.core.model.PokemonDetails
import com.example.core.presentation.designsystem.JetpackApplicationTheme
import com.example.core.presentation.designsystem.PokedexColors
import com.kmpalette.palette.graphics.Palette
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreenRoot(
  viewModel: DetailsViewModel = koinViewModel(),
  onBackScreen: () -> Unit,
) {
  val pokemon by viewModel.pokemon.collectAsStateWithLifecycle()
  val pokemonDetails by viewModel.pokemonDetails.collectAsStateWithLifecycle()
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

  DetailsScreen(
    state = viewModel.state,
    uiState = uiState,
    pokemon = pokemon,
    pokemonDetails = pokemonDetails,
    onAction = { action ->
      when (action) {
        is DetailsAction.OnBackClick -> onBackScreen()
      }
    }
  )
}

@Composable
private fun DetailsScreen(
  state: DetailsState,
  uiState: DetailsUiState,
  pokemon: Pokemon?,
  pokemonDetails: PokemonDetails?,
  onAction: (DetailsAction) -> Unit
) {

  var palette by rememberPaletteState()
  val backgroundBrush by palette.paletteBackgroundBrush()

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .verticalScroll(rememberScrollState()),
  ) {
    DetailsHeader(
      pokemon = pokemon,
      pokemonDetails = pokemonDetails,
      onAction = onAction,
      onPaletteLoaded = { palette = it },
      backgroundBrush = backgroundBrush
    )
  }
}

@Composable
private fun DetailsHeader(
  pokemon: Pokemon?,
  pokemonDetails: PokemonDetails?,
  onAction: (DetailsAction) -> Unit,
  onPaletteLoaded: (Palette) -> Unit,
  backgroundBrush: Brush
) {
  val shape = RoundedCornerShape(
    topStart = 0.dp,
    topEnd = 0.dp,
    bottomStart = 64.dp,
    bottomEnd = 64.dp
  )

  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(290.dp)
      .shadow(elevation = 9.dp, shape = shape)
      .background(brush = backgroundBrush, shape = shape)
  ) {
    Row(
      modifier = Modifier
        .padding(12.dp)
        .statusBarsPadding(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Icon(
        modifier = Modifier
          .padding(end = 6.dp)
          .clickable { onAction(DetailsAction.OnBackClick) },
        painter = painterResource(id = com.example.core.presentation.designsystem.R.drawable.arrow_left),
        tint = PokedexColors.defaultDarkColors().absoluteWhite,
        contentDescription = null
      )

      Text(
        modifier = Modifier.padding(horizontal = 10.dp),
        text = pokemon?.name.orEmpty(),
        color = PokedexColors.defaultDarkColors().absoluteWhite,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
      )
    }
    PokedexText(
      modifier = Modifier
        .align(Alignment.TopEnd)
        .padding(12.dp)
        .statusBarsPadding(),
      text = pokemonDetails?.getIdString().orEmpty(),
      previewText = "#001",
      color = PokedexColors.defaultDarkColors().absoluteWhite,
      fontWeight = FontWeight.Bold,
      fontSize = 18.sp,
    )

    PokemonImage(
      imageUrl = pokemon?.imageUrl.orEmpty(),
      modifier = Modifier
        .align(Alignment.BottomCenter)
        .padding(bottom = 20.dp)
        .size(190.dp),
      onPaletteLoaded = { palette ->
        onPaletteLoaded(palette)
      }
    )
  }
}

@Composable
private fun PokemonImage(
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

@Preview(showBackground = true)
@Composable
private fun DetailsScreenPreview() {
  JetpackApplicationTheme {
    DetailsScreen(
      state = DetailsState(),
      uiState = DetailsUiState.Loading,
      pokemon = Pokemon(
        page = 0,
        nameField = "Pikachu",
        url = "https://pokeapi.co/api/v2/pokemon/1/"
      ),
      pokemonDetails = PokemonDetails(
        id = 1,
        name = "Pikachu",
        height = 4,
        weight = 60,
        experience = 11,
        types = emptyList(),
        stats = emptyList(),
      ),
      onAction = {}
    )
  }
}