plugins {
  `kotlin-dsl`
}

group = "com.example.application.buildlogic"

dependencies {

}

kotlin {
  compilerOptions {
    jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
  }
}
