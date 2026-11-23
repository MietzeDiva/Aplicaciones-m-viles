// Top-level build file where you can add configuration options common to all sub-projects/modules.
val currentLibPath = System.getProperty("java.library.path")
if (currentLibPath != null && currentLibPath.contains("\"")) {
    System.setProperty("java.library.path", currentLibPath.replace("\"", ""))
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ksp) apply false
}