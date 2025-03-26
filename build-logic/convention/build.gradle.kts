plugins {
  `kotlin-dsl`
}

group = "com.example.application.buildlogic"

dependencies {
  compileOnly(libs.android.gradlePlugin)
  compileOnly(libs.android.tools.common)
  compileOnly(libs.kotlin.gradlePlugin)
  compileOnly(libs.ksp.gradlePlugin)
  compileOnly(libs.room.gradlePlugin)
}

gradlePlugin {
  plugins {
    register("androidApplication") {
      id = "application.android.application"
      implementationClass = "AndroidApplicationConventionPlugin"
    }
    register("androidApplicationCompose") {
      id = "application.android.application.compose"
      implementationClass = "AndroidApplicationComposeConventionPlugin"
    }
    register("androidLibrary") {
      id = "application.android.library"
      implementationClass = "AndroidLibraryConventionPlugin"
    }
    register("androidLibraryCompose") {
      id = "application.android.library.compose"
      implementationClass = "AndroidLibraryComposeConventionPlugin"
    }
    register("androidFeatureUi") {
      id = "application.android.feature.ui"
      implementationClass = "AndroidFeatureUiConventionPlugin"
    }
    register("androidRoom") {
      id = "application.android.room"
      implementationClass = "AndroidRoomConventionPlugin"
    }
    register("jvmLibrary") {
      id = "application.jvm.library"
      implementationClass = "JvmLibraryConventionPlugin"
    }
    register("jvmKtor") {
      id = "application.jvm.ktor"
      implementationClass = "JvmKtorConventionPlugin"
    }
  }
}
