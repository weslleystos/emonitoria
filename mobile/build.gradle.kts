plugins {
    id("com.android.application") version Versions.gradlePluginVersion apply false
    id("com.android.library") version Versions.gradlePluginVersion apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlinVersion apply false
}

buildscript {
    dependencies {
        classpath(Dependencies.HiltPlugin)
        classpath(Dependencies.GoogleServices)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}