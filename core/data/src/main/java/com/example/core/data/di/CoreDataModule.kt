package com.example.core.data.di

import com.example.core.data.content.OfflineFirstPokeRepository
import com.example.core.data.networking.HttpClientFactory
import com.example.core.domain.content.PokeRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
  single {
    HttpClientFactory().build()
  }

  singleOf(::OfflineFirstPokeRepository).bind<PokeRepository>()
}