plugins {
  alias(libs.plugins.application.android.library)
}

android {
  namespace = "com.example.content.data"
}

dependencies {
  implementation(libs.kotlinx.coroutines.core)
  implementation(libs.androidx.work)
  implementation(libs.koin.android.workmanager)
  implementation(libs.kotlinx.serialization.json)

  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)

  implementation(projects.core.domain)
  implementation(projects.core.database)
  implementation(projects.content.domain)
}