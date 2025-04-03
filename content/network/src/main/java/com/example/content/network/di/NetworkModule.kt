package com.example.content.network.di

import com.example.content.network.KtorRemotePokemonDataSource
import com.example.core.domain.content.RemotePokemonDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {
  singleOf(::KtorRemotePokemonDataSource).bind<RemotePokemonDataSource>()
}