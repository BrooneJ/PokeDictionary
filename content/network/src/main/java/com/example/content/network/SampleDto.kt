package com.example.content.network

import kotlinx.serialization.Serializable

@Serializable
data class SampleDto(
  val id: String,
  val name: String,
)