//region Startup Plugins

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

//endregion

//region Android

android {
    namespace = "com.jakmos.itemistevolved.network"

    //region Compile

    compileSdkVersion(libs.versions.compile.sdk.get())

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    //endregion

    //region Default Config

    defaultConfig {
        minSdkVersion(libs.versions.min.sdk.get())
        targetSdkVersion(libs.versions.target.sdk.get())
    }

    //endregion

    //region Build Types

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                rootProject.file("settings/proguard/proguard-rules.pro")
            )
            testProguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                rootProject.file("settings/proguard/proguard-rules.pro")
            )
        }
    }

    //endregion

    //region Kotlin Options

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    //endregion
}

//endregion

//region Dependencies

dependencies {
    //region Application Files

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //endregion

    //region Application Modules

    implementation(project(":utility"))

    //endregion

    //region Dependency Injection

    compileOnly(libs.dagger.annotation)
    kapt(libs.dagger.android.processor)
    kapt(libs.dagger.compiler)

    //endregion

    //region Firebase

    api(libs.firebase.config)

    //endregion

    //region Kotlin

    implementation(libs.kotlin.coroutines.play.services)

    //endregion
}

//endregion
