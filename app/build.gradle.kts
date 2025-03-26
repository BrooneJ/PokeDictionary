plugins {
  alias(libs.plugins.application.android.application.compose)
  alias(libs.plugins.application.jvm.ktor)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.mapsplatform.secrets.plugin)
}

android {
  namespace = "com.example.application"

  defaultConfig {
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
}

dependencies {
  implementation(platform(libs.androidx.compose.bom))

  // Coil
  implementation(libs.coil.compose)

  // Compose
  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.material.icons.extended)
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.compose.ui.graphics)
  implementation(libs.androidx.compose.ui.tooling.preview)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.lifecycle.viewmodel.compose)
  implementation(libs.androidx.lifecycle.runtime.compose)
  implementation(libs.androidx.navigation.compose)

  // Core
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)

  // Crypto
  implementation(libs.androidx.security.crypto.ktx)

  // Koin
  implementation(libs.bundles.koin)

  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  debugImplementation(libs.androidx.compose.ui.tooling)
  debugImplementation(libs.androidx.compose.ui.test.manifest)

  // Splash screen
  implementation(libs.androidx.core.splashscreen)

  // Timber
  implementation(libs.timber)

  // Modules
  implementation(projects.core.presentation.designsystem)
  implementation(projects.core.presentation.ui)
  implementation(projects.core.domain)
  implementation(projects.core.database)
  implementation(projects.core.data)

  implementation(projects.auth.presentation)
  implementation(projects.auth.domain)
  implementation(projects.auth.data)

  implementation(projects.content.presentation)
  implementation(projects.content.domain)
  implementation(projects.content.network)
  implementation(projects.content.data)
}