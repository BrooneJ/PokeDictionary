package com.example.content.network.di

import com.example.content.network.KtorRemoteSampleDataSource
import com.example.core.domain.content.RemoteSampleDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {
  singleOf(::KtorRemoteSampleDataSource).bind<RemoteSampleDataSource>()
}