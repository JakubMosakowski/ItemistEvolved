//region Startup Plugins

plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("kapt")
}

//endregion

//region Android

android {
  namespace = "com.jakmos.itemistevolved.utility"

  //region Compile Options

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
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), rootProject.file("settings/proguard/proguard-rules.pro"))
      testProguardFiles(getDefaultProguardFile("proguard-android.txt"), rootProject.file("settings/proguard/proguard-rules.pro"))
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
  //region Androidx

  api(libs.androidx.material)

  //endregion

  //region Debugging

  implementation(libs.timber)

  //endregion

  //region Dependency Injection

  api(libs.dagger.android)
  api(libs.dagger.android.support)
  api(libs.dagger.runtime)
  compileOnly(libs.dagger.annotation)
  kapt(libs.dagger.android.processor)
  kapt(libs.dagger.compiler)

  //endregion

  //region Firebase

  api(platform(libs.firebase.bom))

  //endregion

  //region Kotlin

  api(libs.kotlin.stdlib.jdk8)

  //endregion

  //region Lifecycle

  api(libs.lifecycle.livedata)

  //endregion

  //region Limbo

  api(libs.limbo.mvvm)
  api(libs.limbo.mvvm.dagger)
  api(libs.limbo.recyclerview)

  //endregion

  //region Miscellaneous

  api(libs.jodatime)

  //endregion

  //region Ui

  implementation(libs.lottie)

  //endregion
}

//endregion
