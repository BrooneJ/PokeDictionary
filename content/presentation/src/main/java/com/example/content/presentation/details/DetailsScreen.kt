package com.example.content.presentation.details

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.presentation.designsystem.JetpackApplicationTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreenRoot(
  viewModel: DetailsViewModel = koinViewModel()
) {
  DetailsScreen(
    state = viewModel.state,
    onAction = viewModel::onAction
  )
}

@Composable
private fun DetailsScreen(
  state: DetailsState,
  onAction: (DetailsAction) -> Unit
) {

}

@Preview
@Composable
private fun DetailsScreenPreview() {
  JetpackApplicationTheme {
    DetailsScreen(
      state = DetailsState(),
      onAction = {}
    )
  }
}