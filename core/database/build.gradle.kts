plugins {
  alias(libs.plugins.application.android.library)
  alias(libs.plugins.application.android.room)
}

android {
  namespace = "com.example.core.database"
}

dependencies {
  implementation(libs.org.mongodb.bson)

  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)

  implementation(projects.core.domain)
}