package com.example.content.network.mappers

import com.example.content.network.PokemonResponseDto
import com.example.core.model.Pokemon

fun PokemonResponseDto.toPokemonList(): List<Pokemon> {
  return results.map { pokemonDto ->
    Pokemon(
      page = this.next?.let { next ->
        val offset = next.substringAfter("offset=").substringBefore("&").toInt()
        offset / 20
      } ?: 0,
      nameField = pokemonDto.nameField,
      url = pokemonDto.url
    )
  }
}