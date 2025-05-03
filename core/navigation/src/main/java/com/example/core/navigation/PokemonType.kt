package com.example.core.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.example.core.model.Pokemon
import kotlinx.serialization.json.Json

object PokemonType : NavType<Pokemon>(
  isNullableAllowed = false
) {
  override fun get(
    bundle: Bundle,
    key: String
  ): Pokemon? {
    return Json.decodeFromString(bundle.getString(key) ?: return null)
  }

  override fun put(
    bundle: Bundle,
    key: String,
    value: Pokemon
  ) {
    bundle.putString(key, Json.encodeToString(value))
  }

  override fun parseValue(value: String): Pokemon {
    return Json.decodeFromString(Uri.decode(value))
  }

  override fun serializeAsValue(value: Pokemon): String {
    return Uri.encode(Json.encodeToString(value))
  }
}