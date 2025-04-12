package com.example.content.presentation.home.libs

import androidx.compose.runtime.Stable
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.unit.Constraints
import coil.size.Dimension
import coil.size.Size
import coil.size.SizeResolver
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapNotNull

class ConstraintsSizeResolver : SizeResolver, LayoutModifier {

  private val zeroConstraints = Constraints.fixed(0, 0)
  private val currentConstraints = MutableStateFlow(zeroConstraints)

  override suspend fun size(): Size {
    return currentConstraints
      .mapNotNull(Constraints::toSizeOrNull)
      .first()
  }

  override fun MeasureScope.measure(
    measurable: Measurable,
    constraints: Constraints
  ): MeasureResult {
    // Cache the current constraints.
    currentConstraints.value = constraints

    // Measure an layout the content.
    val placeable = measurable.measure(constraints)
    return layout(placeable.width, placeable.height) {
      placeable.place(0, 0)
    }
  }

  fun setConstraints(constraints: Constraints) {
    currentConstraints.value = constraints
  }
}

@Stable
fun Constraints.toSizeOrNull(): Size? {
  if (isZero) {
    return null
  } else {
    val width = if (hasBoundedWidth) Dimension(maxWidth) else Dimension.Undefined
    val height = if (hasBoundedHeight) Dimension(maxHeight) else Dimension.Undefined
    return Size(width, height)
  }
}