package com.example.content.presentation.details.component

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.core.presentation.designsystem.PokeDicTheme

@Composable
fun BoxScope.PokeDicCircularProgress() {
  CircularProgressIndicator(
    modifier = Modifier.align(Alignment.Center),
    color = PokeDicTheme.colors.primary
  )
}