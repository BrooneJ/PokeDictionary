package com.example.content.presentation.details.libs

import androidx.annotation.FloatRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.core.model.PokemonDetails
import com.example.core.presentation.designsystem.PokeDicTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf


@Immutable
data class PokeDicStatus(
  val type: String,
  @FloatRange(0.0, 1.0) val progress: Float,
  val color: Color,
  val label: String,
)

@Composable
fun PokemonDetails.toPokemonStatusList(): ImmutableList<PokeDicStatus> {
  return persistentListOf(
    PokeDicStatus(
      type = stringResource(id = com.example.content.presentation.R.string.hp),
      progress = hp / PokemonDetails.MAX_HP.toFloat(),
      color = PokeDicTheme.colors.primary,
      label = getHpString()
    ),
    PokeDicStatus(
      type = stringResource(id = com.example.content.presentation.R.string.atk),
      progress = attack / PokemonDetails.MAX_ATTACK.toFloat(),
      color = PokeDicTheme.colors.orange,
      label = getAttackString()
    ),
    PokeDicStatus(
      type = stringResource(id = com.example.content.presentation.R.string.def),
      progress = defense / PokemonDetails.MAX_DEFENSE.toFloat(),
      color = PokeDicTheme.colors.blue,
      label = getDefenseString()
    ),
    PokeDicStatus(
      type = stringResource(id = com.example.content.presentation.R.string.spd),
      progress = speed / PokemonDetails.MAX_SPEED.toFloat(),
      color = PokeDicTheme.colors.flying,
      label = getSpeedString()
    ),
    PokeDicStatus(
      type = stringResource(id = com.example.content.presentation.R.string.exp),
      progress = exp / PokemonDetails.MAX_EXP.toFloat(),
      color = PokeDicTheme.colors.green,
      label = getExpString()
    ),
  )
}
