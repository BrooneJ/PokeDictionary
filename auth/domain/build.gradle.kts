plugins {
  alias(libs.plugins.application.jvm.library)
}

dependencies {
  implementation(projects.core.domain)
}