package com.example.core.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.core.model.PokemonDetails
import kotlinx.serialization.json.Json

@ProvidedTypeConverter
class TypeResponseConverter(
  private val json: Json
) {
  @TypeConverter
  fun fromString(value: String): List<PokemonDetails.TypeResponse> {
    return json.decodeFromString(value)
  }

  @TypeConverter
  fun fromDetailsType(type: List<PokemonDetails.TypeResponse>?): String {
    return json.encodeToString(type)
  }
}