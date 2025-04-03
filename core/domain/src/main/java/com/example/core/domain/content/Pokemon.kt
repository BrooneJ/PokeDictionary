package com.example.core.domain.content

data class Pokemon(
  var page: Int = 0,
  val nameField: String,
  val url: String,
) {

  val name: String
    get() = nameField.replaceFirstChar { it.uppercase() }
}