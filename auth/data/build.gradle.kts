plugins {
  alias(libs.plugins.application.android.library)
  alias(libs.plugins.application.jvm.ktor)
}

android {
  namespace = "com.example.auth.data"
}

dependencies {
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)

  implementation(projects.auth.domain)
  implementation(projects.core.domain)
  implementation(projects.core.data)
}