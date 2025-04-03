package com.example.content.network

import com.example.core.domain.content.Pokemon

fun Pokemon.toCreatePokemonRequest(): CreatePokemonRequest {
  return CreatePokemonRequest(
    id = 0, // FIXME
    name = name,
  )
}