package com.example.content.network

import com.example.core.domain.content.Sample

fun Sample.toCreateSampleRequest(): CreateSampleRequest {
  return CreateSampleRequest(
    id = id,
    name = name,
  )
}