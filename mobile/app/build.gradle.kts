import Dependencies.ActivityCompose
import Dependencies.ComposeJUnitTest
import Dependencies.ComposeMaterial
import Dependencies.ComposePreview
import Dependencies.ComposeTestManifest
import Dependencies.ComposeTooling
import Dependencies.ComposeUI
import Dependencies.CoreKTX
import Dependencies.Espresso
import Dependencies.JUnit
import Dependencies.JUnitExtension
import Dependencies.LifecycleRuntimeKTX

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = BuildConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        minSdk = BuildConfig.minSdk
        targetSdk = BuildConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_11)
        targetCompatibility(JavaVersion.VERSION_11)
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeVersion
    }

    packagingOptions {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
}

dependencies {
    implementation(CoreKTX)

    // Compose
    implementation(ComposeUI)
    implementation(ComposeMaterial)
    implementation(ComposePreview)
    implementation(ActivityCompose)

    // Lifecycle
    implementation(LifecycleRuntimeKTX)

    // Unit Test
    testImplementation(JUnit)

    // UI Test
    androidTestImplementation(Espresso)
    androidTestImplementation(ComposeJUnitTest)
    androidTestImplementation(JUnitExtension)

    // Debug
    debugImplementation(ComposeTooling)
    debugImplementation(ComposeTestManifest)
}