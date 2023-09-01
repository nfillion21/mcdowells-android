plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    kotlin("plugin.serialization") version "1.9.10"
}

android {
    namespace = ConfigData.applicationId
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = ConfigData.applicationId
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(Dependencies.coreAndroidX)
    implementation(Dependencies.lifeCycleRuntime)
    implementation(Dependencies.viewModelCompose)
    implementation(Dependencies.activityCompose)
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeGraphics)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.composeMaterial3)
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.androidTestJUnit)
    androidTestImplementation(Dependencies.androidTestEspresso)
    androidTestImplementation(platform(Dependencies.androidTestComposeBom))
    androidTestImplementation(Dependencies.androidTestComposeUi)
    debugImplementation(Dependencies.debugComposeUiTooling)
    debugImplementation(Dependencies.debugComposeUiTestManifest)

    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltAndroidCompiler)

    implementation(Dependencies.ktorAndroidClient)
    implementation(Dependencies.ktorSerializationJSON)
    implementation(Dependencies.ktorContentNegotiation)
    implementation(Dependencies.ktorClientLogging)

    implementation(Dependencies.paging)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}