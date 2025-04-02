package com.example.content.network

import kotlinx.serialization.Serializable

@Serializable
data class CreateSampleRequest(
  val id: Int,
  val name: String,
)