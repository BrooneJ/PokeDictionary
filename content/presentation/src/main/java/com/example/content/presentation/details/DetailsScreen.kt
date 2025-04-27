package com.example.content.presentation.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.presentation.designsystem.JetpackApplicationTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreenRoot(
  viewModel: DetailsViewModel = koinViewModel()
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

  DetailsScreen(
    state = viewModel.state,
    uiState = uiState,
    onAction = viewModel::onAction
  )
}

@Composable
private fun DetailsScreen(
  state: DetailsState,
  uiState: DetailsUiState,
  onAction: (DetailsAction) -> Unit
) {

}

@Preview
@Composable
private fun DetailsScreenPreview() {
  JetpackApplicationTheme {
    DetailsScreen(
      state = DetailsState(),
      uiState = DetailsUiState.Loading,
      onAction = {}
    )
  }
}