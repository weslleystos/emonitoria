import Dependencies.Dagger
import Dependencies.DaggerCompiler

plugins {
    id("kotlin")
    kotlin("kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    // Dagger
    implementation(Dagger)
    kapt(DaggerCompiler)
}