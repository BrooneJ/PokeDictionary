plugins {
  alias(libs.plugins.application.android.library)
}

android {
  namespace = "com.example.content.network"
}

dependencies {
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)

  implementation(projects.core.domain)
  implementation(projects.core.data)
}