package com.example.content.presentation.details.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.content.presentation.details.libs.PokeDicStatus
import com.example.core.presentation.designsystem.PokeDicTheme

@Composable
fun PokemonStatusItem(
  modifier: Modifier = Modifier,
  pokeDicStatus: PokeDicStatus,
) {
  Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceEvenly,
  ) {
    Text(
      modifier = Modifier
        .padding(start = 32.dp)
        .widthIn(min = 20.dp),
      text = pokeDicStatus.type,
      color = PokeDicTheme.colors.white70,
      fontWeight = FontWeight.Bold,
      fontSize = 12.sp,
    )


  }
}