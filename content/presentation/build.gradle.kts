plugins {
  alias(libs.plugins.application.android.feature.ui)
}

android {
  namespace = "com.example.content.presentation"
}

dependencies {
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.coil.compose)
  implementation(libs.androidx.activity.compose)
  implementation(libs.timber)
  implementation(libs.kmpalette.core)
  implementation(libs.kotlinx.immutable.collection)

  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)

  implementation(projects.core.domain)
  implementation(projects.content.domain)
}