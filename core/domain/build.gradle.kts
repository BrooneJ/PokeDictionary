plugins {
  alias(libs.plugins.application.android.library)
  alias(libs.plugins.kotlin.serialization)
}

android {
  namespace = "com.example.core.domain"
}

dependencies {
  api(projects.core.model)

  implementation(libs.kotlinx.coroutines.core)
  implementation(libs.kotlinx.serialization.json)
}