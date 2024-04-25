plugins {
    alias(libs.plugins.jetbrainsKotlinJvm)
    alias(libs.plugins.kotlin.plugin.serialization)
}

dependencies {
    implementation(project(":utils"))
    implementation(libs.javax.inject)
    implementation(libs.kotlinx.serialization.json)
    //Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
}