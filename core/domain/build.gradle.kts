plugins {
  alias(libs.plugins.application.jvm.library)
  alias(libs.plugins.kotlin.serialization)
}

dependencies {
  implementation(libs.kotlinx.coroutines.core)
  implementation(libs.kotlinx.serialization.json)
}