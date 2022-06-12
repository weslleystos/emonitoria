import Versions.activityComposeVersion
import Versions.androidXTestVersion
import Versions.composeVersion
import Versions.coreKTXVersion
import Versions.daggerVersion
import Versions.espressoVersion
import Versions.hiltVersion
import Versions.junitVersion
import Versions.lifecycleRuntimeKTXVersion

object Dependencies {
    const val CoreKTX = "androidx.core:core-ktx:$coreKTXVersion"

    // Compose
    const val ComposeUI = "androidx.compose.ui:ui:$composeVersion"
    const val ComposeMaterial = "androidx.compose.material:material:$composeVersion"
    const val ComposePreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
    const val ComposeTooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val ActivityCompose = "androidx.activity:activity-compose:$activityComposeVersion"

    // Lifecycle
    const val LifecycleRuntimeKTX =
        "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleRuntimeKTXVersion"

    // Unit Test
    const val JUnit = "junit:junit:$junitVersion"

    // UI Test
    const val Espresso = "androidx.test.espresso:espresso-core:$espressoVersion"
    const val JUnitExtension = "androidx.test.ext:junit:$androidXTestVersion"
    const val ComposeJUnitTest = "androidx.compose.ui:ui-test-junit4:$composeVersion"
    const val ComposeTestManifest = "androidx.compose.ui:ui-test-manifest:$composeVersion"

    // Hilt
    const val Hilt = "com.google.dagger:hilt-android:$hiltVersion"
    const val HiltCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
    const val HiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"

    // Dagger
    const val Dagger = "com.google.dagger:dagger:$daggerVersion"
    const val DaggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"

}
