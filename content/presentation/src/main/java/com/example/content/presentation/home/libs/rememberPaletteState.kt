package com.example.content.presentation.home.libs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SnapshotMutationPolicy
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.structuralEqualityPolicy
import com.kmpalette.palette.graphics.Palette

@Composable
fun rememberPaletteState(
  value: Palette? = null,
  policy: SnapshotMutationPolicy<Palette?> = structuralEqualityPolicy(),
): MutableState<Palette?> = remember(key1 = value) {
  mutableStateOf(value = value, policy = policy)
}