package com.example.content.presentation.details

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.content.presentation.details.component.PokeDicCircularProgress
import com.example.content.presentation.details.component.PokeDicText
import com.example.content.presentation.details.component.PokemonDetailItem
import com.example.content.presentation.details.component.PokemonStatusItem
import com.example.content.presentation.details.component.paletteBackgroundBrush
import com.example.content.presentation.details.libs.rememberPaletteState
import com.example.content.presentation.details.libs.toPokemonStatusList
import com.example.content.presentation.libs.PokemonImage
import com.example.core.model.Pokemon
import com.example.core.model.PokemonDetails
import com.example.core.presentation.designsystem.JetpackApplicationTheme
import com.example.core.presentation.designsystem.PokeDicTheme
import com.example.core.presentation.designsystem.PokedexColors
import com.example.core.presentation.designsystem.utils.getPokemonTypeColor
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
      .fillMaxSize()
      .verticalScroll(rememberScrollState()),
  ) {
    DetailsHeader(
      pokemon = pokemon,
      pokemonDetails = pokemonDetails,
      onAction = onAction,
      onPaletteLoaded = { palette = it },
      backgroundBrush = backgroundBrush
    )

    if (uiState == DetailsUiState.Idle && pokemonDetails != null) {
      DetailsInfo(pokemonDetails = pokemonDetails)
      DetailsStatus(pokemonDetails = pokemonDetails)
    } else {
      Box(modifier = Modifier.fillMaxSize()) {
        PokeDicCircularProgress()
      }
    }
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
        tint = PokedexColors.defaultLightColors().black,
        contentDescription = null
      )

      Text(
        modifier = Modifier.padding(horizontal = 10.dp),
        text = pokemon?.name.orEmpty(),
        color = PokedexColors.defaultLightColors().black,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
      )
    }
    PokeDicText(
      modifier = Modifier
        .align(Alignment.TopEnd)
        .padding(12.dp)
        .statusBarsPadding(),
      text = pokemonDetails?.getIdString().orEmpty(),
      previewText = "#001",
      color = PokedexColors.defaultLightColors().black,
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

  PokeDicText(
    modifier = Modifier
      .padding(top = 24.dp)
      .fillMaxWidth(),
    text = pokemon?.name.orEmpty(),
    previewText = "Pikachu",
    color = PokedexColors.defaultLightColors().black,
    fontWeight = FontWeight.Bold,
    textAlign = TextAlign.Center,
    fontSize = 36.sp
  )
}

@Composable
private fun DetailsInfo(pokemonDetails: PokemonDetails) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 14.dp),
    horizontalArrangement = Arrangement.spacedBy(22.dp, Alignment.CenterHorizontally)
  ) {
    pokemonDetails.types.forEach { typeInfo ->
      Text(
        modifier = Modifier
          .background(
            color = getPokemonTypeColor(type = typeInfo.type.name),
            shape = RoundedCornerShape(64.dp)
          )
          .padding(horizontal = 40.dp, vertical = 4.dp),
        text = typeInfo.type.name,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        color = PokeDicTheme.colors.absoluteWhite,
        maxLines = 1,
        fontSize = 16.sp
      )
    }
  }

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 24.dp),
    horizontalArrangement = Arrangement.SpaceEvenly
  ) {
    PokemonDetailItem(
      title = pokemonDetails.getWeightString(),
      content = stringResource(id = com.example.content.presentation.R.string.weight),
    )

    PokemonDetailItem(
      title = pokemonDetails.getHeightString(),
      content = stringResource(id = com.example.content.presentation.R.string.height),
    )
  }
}

@Composable
private fun DetailsStatus(
  pokemonDetails: PokemonDetails,
) {
  Text(
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 22.dp, bottom = 16.dp),
    text = stringResource(id = com.example.content.presentation.R.string.base_stats),
    textAlign = TextAlign.Center,
    color = PokeDicTheme.colors.black,
    fontWeight = FontWeight.Bold,
    fontSize = 21.sp,
  )

  Column {
    pokemonDetails.toPokemonStatusList().forEach { pokemonStatus ->
      PokemonStatusItem(
        modifier = Modifier.padding(bottom = 12.dp),
        pokeDicStatus = pokemonStatus
      )
    }
  }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DetailInfoPreview() {
  JetpackApplicationTheme {
    DetailsInfo(
      pokemonDetails = PokemonDetails(
        id = 1,
        name = "Pikachu",
        height = 4,
        weight = 60,
        experience = 11,
        types = emptyList(),
        stats = emptyList(),
      )
    )
  }
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
        types = listOf(
          PokemonDetails.TypeResponse(slot = 0, type = PokemonDetails.Type("grass")),
          PokemonDetails.TypeResponse(slot = 0, type = PokemonDetails.Type("poison")),
        ),
        stats = emptyList(),
      ),
      onAction = {}
    )
  }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PokedicDetailsStatusPreview() {
  JetpackApplicationTheme {
    DetailsStatus(
      pokemonDetails = PokemonDetails(
        id = 1,
        name = "Pikachu",
        height = 4,
        weight = 60,
        experience = 11,
        types = listOf(
          PokemonDetails.TypeResponse(slot = 0, type = PokemonDetails.Type("grass")),
          PokemonDetails.TypeResponse(slot = 0, type = PokemonDetails.Type("poison")),
        ),
        stats = listOf(
          PokemonDetails.StatsResponse(baseStat = 20, effort = 0, stat = PokemonDetails.Stat("hp")),
          PokemonDetails.StatsResponse(baseStat = 40, effort = 0, stat = PokemonDetails.Stat("attack")),
          PokemonDetails.StatsResponse(baseStat = 60, effort = 0, stat = PokemonDetails.Stat("defense")),
          PokemonDetails.StatsResponse(baseStat = 80, effort = 0, stat = PokemonDetails.Stat("attack")),
        ),
      )
    )
  }
}