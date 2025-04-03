plugins {
  alias(libs.plugins.application.android.library)
  alias(libs.plugins.application.jvm.ktor)
}

android {
  namespace = "com.example.content.network"
}

dependencies {
  implementation(libs.bundles.koin)
  implementation(libs.androidx.compose.runtime)

  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)

  implementation(projects.core.domain)
  implementation(projects.core.data)
}