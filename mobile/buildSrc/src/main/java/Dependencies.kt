import Versions.activityComposeVersion
import Versions.androidXTestVersion
import Versions.composeVersion
import Versions.coreKTXVersion
import Versions.espressoVersion
import Versions.junitVersion
import Versions.lifecycleRuntimeKTXVersion

object Dependencies {
    const val CoreKTX = "androidx.core:core-ktx:$coreKTXVersion"

    const val ComposeUI = "androidx.compose.ui:ui:$composeVersion"
    const val ComposeMaterial = "androidx.compose.material:material:$composeVersion"
    const val ComposePreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
    const val ComposeJUnitTest = "androidx.compose.ui:ui-test-junit4:$composeVersion"
    const val ComposeTooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val ComposeTestManifest = "androidx.compose.ui:ui-test-manifest:$composeVersion"

    const val LifecycleRuntimeKTX =
        "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleRuntimeKTXVersion"
    const val ActivityCompose = "androidx.activity:activity-compose:$activityComposeVersion"

    const val JUnit = "junit:junit:$junitVersion"
    const val JUnitExtension = "androidx.test.ext:junit:$androidXTestVersion"
    const val Espresso = "androidx.test.espresso:espresso-core:$espressoVersion"

}
