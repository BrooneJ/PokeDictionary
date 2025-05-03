package com.example.content.network.mappers

import com.example.content.network.PokemonDetailsDto
import com.example.core.model.PokemonDetails

fun PokemonDetailsDto.toPokemonDetails(): PokemonDetails {
  return PokemonDetails(
    id = id,
    name = name,
    height = height,
    weight = weight,
    experience = experience,
    types = types.map { PokemonDetails.TypeResponse(it.slot, it.type.toDomain()) },
    stats = stats.map { PokemonDetails.StatsResponse(it.baseStat, it.effort, it.stat.toDomain()) }
  )
}

fun PokemonDetailsDto.Type.toDomain(): PokemonDetails.Type {
  return PokemonDetails.Type(
    name = name,
  )
}

fun PokemonDetailsDto.Stat.toDomain(): PokemonDetails.Stat {
  return PokemonDetails.Stat(
    name = name,
  )
}