package com.example.core.navigation

import android.net.Uri
import android.os.Bundle
import androidx.core.os.BundleCompat
import androidx.navigation.NavType
import com.example.core.model.Pokemon
import kotlinx.serialization.json.Json

object PokemonType : NavType<Pokemon>(isNullableAllowed = false) {

  override fun put(
    bundle: Bundle, key: String, value: Pokemon
  ) {
    bundle.putParcelable(key, value)
  }

  override fun get(bundle: Bundle, key: String): Pokemon? =
    BundleCompat.getParcelable(bundle, key, Pokemon::class.java)

  override fun parseValue(value: String): Pokemon {
    return Json.decodeFromString(Uri.decode(value))
  }

  override fun serializeAsValue(value: Pokemon): String {
    return Uri.encode(Json.encodeToString(value))
  }
}