package com.example.content.presentation.details

sealed interface DetailsAction {
  data object OnBackClick : DetailsAction
}