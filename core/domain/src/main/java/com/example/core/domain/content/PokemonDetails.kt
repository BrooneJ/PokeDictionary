package com.example.core.domain.content

import kotlin.random.Random

data class PokemonDetails(
  val id: Int,
  val name: String,
  val height: Int,
  val weight: Int,
  val experience: Int,
  val types: List<TypeResponse>,
  val stats: List<StatsResponse>,
  val exp: Int = Random.nextInt(MAX_EXP)
) {
  val hp: Int by lazy {
    stats.firstOrNull { it.stat.name == "hp" }?.baseStat ?: Random.nextInt(MAX_HP)
  }
  val attack: Int by lazy {
    stats.firstOrNull { it.stat.name == "attack" }?.baseStat ?: Random.nextInt(MAX_ATTACK)
  }
  val defense: Int by lazy {
    stats.firstOrNull { it.stat.name == "defense" }?.baseStat ?: Random.nextInt(MAX_DEFENSE)
  }
  val speed: Int by lazy {
    stats.firstOrNull { it.stat.name == "speed" }?.baseStat ?: Random.nextInt(MAX_SPEED)
  }

  fun getIdString(): String = String.format("#%03d", id)
  fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)
  fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)
  fun getHpString(): String = " $hp/$MAX_HP"
  fun getAttackString(): String = " $attack/$MAX_ATTACK"
  fun getDefenseString(): String = " $defense/$MAX_DEFENSE"
  fun getSpeedString(): String = " $speed/$MAX_SPEED"
  fun getExpString(): String = " $exp/$MAX_EXP"

  data class TypeResponse(
    val slot: Int,
    val type: Type
  )

  data class StatsResponse(
    val baseStat: Int,
    val effort: Int,
    val stat: Stat
  )

  data class Stat(
    val name: String
  )

  data class Type(
    val name: String
  )

  companion object {
    const val MAX_HP = 300
    const val MAX_ATTACK = 300
    const val MAX_DEFENSE = 300
    const val MAX_SPEED = 300
    const val MAX_EXP = 1000
  }
}
