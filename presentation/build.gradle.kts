plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
    id("kotlin-parcelize")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.presentation"
    compileSdk = 34

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":utils"))

    implementation(libs.dotsindicator)

    // For collectAsStateWithLifecycle
    implementation(libs.androidx.lifecycle.runtime.compose)
    // viewModel
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    //Hilt
    implementation (libs.hilt.android)
    ksp (libs.hilt.compiler)
    ksp (libs.androidx.hilt.compiler)
    implementation (libs.androidx.hilt.navigation.compose)
    //Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    // For navigation
    implementation (libs.androidx.navigation.compose)

    //Coil
    implementation(libs.coil.compose)
    //Lottie
    implementation(libs.lottie.compose)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    debugImplementation(libs.ui.tooling)
}