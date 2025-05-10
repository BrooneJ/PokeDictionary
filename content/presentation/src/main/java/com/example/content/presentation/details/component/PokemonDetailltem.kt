package com.example.content.presentation.details.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.presentation.designsystem.PokeDicTheme

@Composable
fun PokemonDetailItem(
  title: String?,
  content: String?,
) {
  Column(horizontalAlignment = Alignment.CenterHorizontally) {
    PokeDicText(
      modifier = Modifier.padding(10.dp),
      text = title.orEmpty(),
      previewText = "24.0 KG",
      color = PokeDicTheme.colors.black,
      fontWeight = FontWeight.Bold,
      fontSize = 21.sp
    )

    PokeDicText(
      text = content.orEmpty(),
      previewText = "Weight",
      color = PokeDicTheme.colors.white56,
      fontWeight = FontWeight.Bold,
      fontSize = 12.sp
    )
  }
}