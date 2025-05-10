package com.example.core.database.entity.mapper

import com.example.core.database.entity.PokemonDetailsEntity
import com.example.core.model.PokemonDetails

object PokemonDetailsEntityMapper : EntityMapper<PokemonDetails, PokemonDetailsEntity> {

  override fun asEntity(domain: PokemonDetails): PokemonDetailsEntity {
    return PokemonDetailsEntity(
      id = domain.id,
      name = domain.name,
      height = domain.height,
      weight = domain.weight,
      experience = domain.experience,
      types = domain.types,
      stats = domain.stats,
      exp = domain.exp,
    )
  }

  override fun asDomain(entity: PokemonDetailsEntity): PokemonDetails {
    return PokemonDetails(
      id = entity.id,
      name = entity.name,
      height = entity.height,
      weight = entity.weight,
      experience = entity.experience,
      types = entity.types,
      stats = entity.stats,
      exp = entity.exp
    )
  }
}

fun PokemonDetails.asEntity(): PokemonDetailsEntity {
  return PokemonDetailsEntityMapper.asEntity(this)
}

fun PokemonDetailsEntity.asDomain(): PokemonDetails {
  return PokemonDetailsEntityMapper.asDomain(this)
}