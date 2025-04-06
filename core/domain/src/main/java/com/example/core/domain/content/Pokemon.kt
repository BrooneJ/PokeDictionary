package com.example.core.domain.content

data class Pokemon(
  var page: Int = 0,
  val nameField: String,
  val url: String,
) {

  val name: String
    get() = nameField.replaceFirstChar { it.uppercase() }

  val imageUrl: String
    get() {
      val index = url.split("/".toRegex()).dropLast(1).last()
      return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
    }
}