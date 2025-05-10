plugins {
  alias(libs.plugins.application.android.library)
  alias(libs.plugins.application.android.room)
  alias(libs.plugins.kotlin.serialization)
}

android {
  namespace = "com.example.core.database"
}

dependencies {
  implementation(libs.org.mongodb.bson)
  implementation(libs.bundles.koin)
  implementation(libs.kotlinx.serialization.json)

  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)

  implementation(projects.core.domain)
}