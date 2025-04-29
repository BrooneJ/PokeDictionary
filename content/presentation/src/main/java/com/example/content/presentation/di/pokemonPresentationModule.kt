package com.example.content.presentation.di

import com.example.content.presentation.details.DetailsViewModel
import com.example.content.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val pokemonPresentationModule = module {
  viewModelOf(::HomeViewModel)
  viewModelOf(::DetailsViewModel)
}