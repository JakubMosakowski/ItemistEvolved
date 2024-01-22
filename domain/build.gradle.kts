//region Startup Plugins

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

//endregion

//region Android

android {
    namespace = "com.jakmos.itemistevolved.domain"

    //region Build Configuration

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), rootProject.file("settings/proguard/proguard-rules.pro"))
            testProguardFiles(getDefaultProguardFile("proguard-android.txt"), rootProject.file("settings/proguard/proguard-rules.pro"))
        }
    }

    //endregion

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

    //region Kotlin Options

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    //endregion
}

//endregion

//region Dependencies

dependencies {
    //region Annotation Processors

    kapt(libs.dagger.android.processor)
    kapt(libs.dagger.compiler)
    kapt(libs.mapstruct.processor)

    //endregion

    //region Application Modules

    api(project(":utility"))
    implementation(project(":network"))
    implementation(project(":persistence"))

    //endregion

    //region Dependency Injection

    compileOnly(libs.dagger.annotation)

    //endregion

    //region Mapping

    api(libs.mapstruct)

    //endregion
}

//endregion
