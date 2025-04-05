package com.example.content.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.presentation.designsystem.JetpackApplicationTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreenRoot(
    viewModel: HomeViewModel = koinViewModel()
) {
    HomeScreen(
        state = viewModel.state,
    )
}

@Composable
private fun HomeScreen(
    state: HomeState,
) {
    Box {
        Column {
            Text("hello")
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    JetpackApplicationTheme {
        HomeScreen(
            state = HomeState(),
        )
    }
}