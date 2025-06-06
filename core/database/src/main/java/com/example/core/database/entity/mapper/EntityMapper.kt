package com.example.core.database.entity.mapper

interface EntityMapper<Domain, Entity> {

  fun asEntity(domain: Domain): Entity

  fun asDomain(entity: Entity): Domain
}