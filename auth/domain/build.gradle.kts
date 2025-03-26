plugins {
  alias(libs.plugins.application.android.jvm.library)
}

dependencies {
  implementation(projects.core.domain)
}