package com.example.content.presentation.details.component

import android.content.res.Configuration
import androidx.annotation.FloatRange
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.content.presentation.details.utils.pxToDp
import com.example.core.presentation.designsystem.JetpackApplicationTheme
import com.example.core.presentation.designsystem.PokeDicTheme

@Composable
fun PokedicProgressBar(
  modifier: Modifier = Modifier,
  @FloatRange(0.0, 1.0) progress: Float,
  color: Color,
  label: String,
) {
  val screenWidth = LocalConfiguration.current.screenWidthDp.dp.value
  val isLocalInspectionMode = LocalInspectionMode.current
  var progressWidth by remember {
    mutableFloatStateOf(
      if (isLocalInspectionMode) {
        screenWidth
      } else {
        0f
      }
    )
  }

  Box(
    modifier = modifier
      .fillMaxWidth()
      .height(18.dp)
      .onSizeChanged { progressWidth = it.width * progress }
      .background(
        color = PokeDicTheme.colors.absoluteWhite,
        shape = RoundedCornerShape(64.dp)
      )
      .clip(RoundedCornerShape(64.dp))
  ) {
    var textWidth by remember { mutableIntStateOf(0) }
    val threshold = 16
    val isInner by remember(
      progressWidth,
      textWidth
    ) { mutableStateOf(progressWidth > (textWidth + threshold * 2)) }

    val animation: Float by animateFloatAsState(
      targetValue = if (progressWidth == 0f) 0f else 1f,
      animationSpec = tween(durationMillis = 950, easing = LinearOutSlowInEasing),
      label = "",
    )

    Box(
      modifier = Modifier
        .align(Alignment.CenterStart)
        .width(
          progressWidth
            .toInt()
            .pxToDp() * animation,
        )
        .height(18.dp)
        .background(
          color = color,
          shape = RoundedCornerShape(64.dp)
        )
    ) {
      if (isInner) {
        Text(
          modifier = Modifier
            .onSizeChanged { textWidth = it.width }
            .align(Alignment.CenterEnd)
            .padding(end = (threshold * 2).pxToDp()),
          text = label,
          fontSize = 12.sp,
          color = PokeDicTheme.colors.absoluteWhite
        )
      }

      if (!isInner) {
        Text(
          modifier = Modifier
            .onSizeChanged { textWidth = it.width }
            .align(Alignment.CenterStart)
            .padding(
              start = progressWidth
                .toInt()
                .pxToDp() + threshold.pxToDp()
            ),
          text = label,
          fontSize = 12.sp,
          color = PokeDicTheme.colors.absoluteBlack
        )
      }
    }
  }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PokedicProgressBarPreview1() {
  JetpackApplicationTheme {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(120.dp)
        .background(PokeDicTheme.colors.background)
    ) {
      PokedicProgressBar(
        modifier = Modifier.align(Alignment.Center),
        progress = 0.1f,
        color = PokeDicTheme.colors.primary,
        label = "150/300"
      )
    }
  }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PokedicProgressBarPreview2() {
  JetpackApplicationTheme {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(120.dp)
        .background(PokeDicTheme.colors.background)
    ) {
      PokedicProgressBar(
        modifier = Modifier
          .fillMaxWidth()
          .align(Alignment.Center),
        progress = 0.5f,
        color = PokeDicTheme.colors.primary,
        label = "150/300"
      )
    }
  }
}