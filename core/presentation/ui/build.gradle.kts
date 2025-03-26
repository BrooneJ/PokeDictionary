plugins {
  alias(libs.plugins.application.android.library.compose)
}

android {
  namespace = "com.example.core.presentation.ui"
}

dependencies {
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.compose.ui.graphics)
  implementation(libs.androidx.lifecycle.runtime.compose)
  implementation(libs.androidx.lifecycle.viewmodel.compose)
  implementation(libs.androidx.compose.ui.tooling.preview)
  implementation(libs.androidx.compose.material3)

  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)

  implementation(projects.core.domain)
  implementation(projects.core.presentation.designsystem)
}