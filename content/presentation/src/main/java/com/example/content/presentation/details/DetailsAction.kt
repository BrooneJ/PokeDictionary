package com.example.content.presentation.details

sealed interface DetailsAction {
  data object FetchDetails : DetailsAction
}