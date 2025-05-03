plugins {
  alias(libs.plugins.application.android.library.compose)
  alias(libs.plugins.kotlin.serialization)
}

android {
  namespace = "com.example.core.navigation"
}

dependencies {
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  implementation(libs.kotlinx.serialization.json)
  implementation(libs.androidx.navigation.compose)

  testImplementation(libs.junit)

  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)

  implementation(projects.core.domain)
}