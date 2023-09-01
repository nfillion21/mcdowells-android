object Dependencies {
    val coreAndroidX by lazy {"androidx.core:core-ktx:${Versions.coreAndroidX}"}
    val lifeCycleRuntime by lazy {"androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycleRuntime}"}
    val viewModelCompose by lazy {"androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifeCycleRuntime}"}
    val activityCompose by lazy {"androidx.activity:activity-compose:${Versions.activityCompose}"}
    val composeBom by lazy {"androidx.compose:compose-bom:${Versions.composeBom}"}
    val composeUi by lazy {"androidx.compose.ui:${Versions.composeUi}"}
    val composeGraphics by lazy {"androidx.compose.ui:${Versions.composeGraphics}"}
    val composeUiToolingPreview by lazy {"androidx.compose.ui:${Versions.composeUiToolingPreview}"}
    val composeMaterial3 by lazy {"androidx.compose.material3:${Versions.composeMaterial3}"}

    val junit by lazy {"junit:junit:${Versions.junit}"}
    val androidTestJUnit by lazy {"androidx.test.ext:junit:${Versions.androidTestJUnit}"}
    val androidTestEspresso by lazy {"androidx.test.espresso:espresso-core:${Versions.androidTestEspresso}"}
    val androidTestComposeBom by lazy {"androidx.compose:compose-bom:${Versions.androidTestComposeBom}"}
    val androidTestComposeUi by lazy {"androidx.compose.ui:${Versions.androidTestComposeUi}"}
    val debugComposeUiTooling by lazy {"androidx.compose.ui:${Versions.debugComposeUiTooling}"}
    val debugComposeUiTestManifest by lazy {"androidx.compose.ui:${Versions.debugComposeUiTestManifest}"}

    // Hilt
    val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
    val hiltAndroidCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hilt}" }

    // Ktor
    val ktorAndroidClient by lazy { "io.ktor:ktor-client-android:${Versions.ktor}" }
    val ktorSerializationJSON by lazy { "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}" }
    val ktorContentNegotiation by lazy { "io.ktor:ktor-client-content-negotiation:${Versions.ktor}" }
    val ktorClientLogging by lazy { "io.ktor:ktor-client-logging:${Versions.ktor}" }

    // Paging
    val paging by lazy {"androidx.paging:paging-compose:${Versions.paging}"}

    /*
    // Coroutine
    val workRuntime by lazy { "androidx.work:work-runtime-ktx:${Versions.workRuntime}" }

    // Room
    val roomRuntine by lazy {"androidx.room:room-runtime:${Versions.room}"}
    val roomKtx by lazy {"androidx.room:room-ktx:${Versions.room}"}
    val roomCompiler by lazy {"androidx.room:room-compiler:${Versions.room}"}

    // datastore
    val datastorePreferences by lazy {"androidx.datastore:datastore-preferences:${Versions.datastoreVersion}"}

    val coilCompose by lazy {"io.coil-kt:coil-compose:${Versions.coilCompose}"}
    */
}