import org.json.JSONObject
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Properties
import java.util.TimeZone

plugins {
    id("com.android.application")
    id("com.google.firebase.appdistribution")
    id("com.google.firebase.crashlytics")
    id("com.google.gms.google-services")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

//region Android

android {
    namespace = "com.jakmos.itemistevolved"

    //region Build Configuration

    signingConfigs {
        getByName("debug") {
            keyAlias = "androiddebugkey"
            keyPassword = "android"
            storePassword = "android"
            storeFile = rootProject.file("settings/keystore/debug.jks")
        }

        create("release") {
            val keystorePropertiesFile = rootProject.file("settings/keystore/keystore.properties")
            if (!keystorePropertiesFile.exists()) {
                keystorePropertiesFile.createNewFile()
            }
            val keystoreProperties = Properties()
            keystoreProperties.load(FileInputStream(keystorePropertiesFile))

            keyAlias = keystoreProperties["keyAlias"] as String?
            keyPassword = keystoreProperties["keyPassword"] as String?
            storePassword = keystoreProperties["storePassword"] as String?
            storeFile = rootProject.file("settings/keystore/release.jks")
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("release")
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

    //region Compile Options

    compileSdkVersion(libs.versions.compile.sdk.get())

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    //endregion

    //region Data Binding

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    //endregion

    //region Default Config

    defaultConfig {
        minSdkVersion(libs.versions.min.sdk.get())
        targetSdkVersion(libs.versions.target.sdk.get())
        applicationId = "com.jakmos.itemistevolved"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments(mapOf("clearPackageData" to "true"))
    }

    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }

    //endregion

    //region Flavor Dimensions

    flavorDimensions("type")

    //endregion

    //region Gradle Properties

    //endregion

    //region Kotlin Options

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    //endregion

    //region Product Flavors

    productFlavors {
        create("dev") {
            applicationIdSuffix = ".dev"
            dimension = "type"
            firebaseAppDistribution {
                releaseNotesFile = "settings/distribute/release-notes.txt"
                serviceCredentialsFile = "settings/distribute/firebase-app-distribution.json"
                groups = "internal"
            }
            resValue("string", "app_name", "${getProjectName()} Development")
            versionCode = getAutoVersionCode()
            versionName = "${getAutoVersionName()} (dev)"
        }
        create("prod") {
            dimension = "type"
            firebaseAppDistribution {
                releaseNotesFile = "settings/distribute/release-notes.txt"
                serviceCredentialsFile = "settings/distribute/firebase-app-distribution.json"
                groups = "internal"
            }
            resValue("string", "app_name", getProjectName())
            versionCode = getAutoVersionCode()
            versionName = getAutoVersionName()
        }
    }

    // Print version code and name.
    printVersionCode()

    //endregion
}

//endregion

//region Dependencies

dependencies {

    //region Application Modules

    implementation(project(":domain"))

    //endregion

    //region AndroidX

    implementation(libs.androidx.constraint)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.swiperefreshlayout)

    //endregion

    //region Dependency Injection

    compileOnly(libs.dagger.annotation)
    kapt(libs.dagger.android.processor)
    kapt(libs.dagger.compiler)

    //endregion

    //region Fast Adapter

    implementation(libs.fast.adapter.binding)
    implementation(libs.fast.adapter.core)
    implementation(libs.fast.adapter.diff)
    implementation(libs.fast.adapter.drag)
    implementation(libs.fast.adapter.expandable)
    implementation(libs.fast.adapter.ui)
    implementation(libs.fast.adapter.utils)

    //endregion

    //region Firebase

    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.messaging)

    //endregion

    //region Ui

    implementation(libs.lottie)
    implementation(libs.material.dialogs)

    //endregion

    //region Kotlin

    implementation(libs.kotlin.coroutines)

    //endregion

    //region Unit, Instrumentation and Integration Test

    androidTestImplementation(libs.test.barista)
    androidTestImplementation(libs.test.espresso)
    androidTestImplementation(libs.test.junit.ktx)
    androidTestImplementation(libs.test.mockito.core)
    androidTestImplementation(libs.test.mockito.inline)
    androidTestImplementation(libs.test.rules)
    androidTestImplementation(libs.test.runner)
    androidTestImplementation(libs.test.test.core)
    androidTestImplementation(libs.test.truth)
    androidTestUtil(libs.test.orchestrator)

    testImplementation(libs.test.core.testing)
    testImplementation(libs.test.coroutines.test)
    testImplementation(libs.test.junit)
    testImplementation(libs.test.mockito.core)
    testImplementation(libs.test.mockito.inline)
    testImplementation(libs.test.truth)

    //endregion
}

//endregion

tasks.register("downloadReleaseNotesTask") {
    doLast {
        val githubToken = project.findProperty("github_token") as String?
        downloadReleaseNotes(githubToken)
    }
}

tasks.register("createReleaseNotesTask") {
    doLast {
        val latestCommit = getLatestSavedCommit()
        val releaseNotes = getReleaseNotes()

        File("settings/distribute/release-notes.txt").writeText(releaseNotes)

        println("\n\n---------------- RELEASE NOTES (since commit: $latestCommit) ----------------\n")
        println(File("settings/distribute/release-notes.txt").readText().trim())
        println("\n-------------------------------------------------------------------\n")
    }
}

fun getProjectName() = "ItemistEvolved"

fun printVersionCode() {
    println("\n---------- VERSION DATA ----------\n")
    println("-> CODE: ${getAutoVersionCode()}")
    println("-> NAME: ${getAutoVersionName()}\n")
    println("-> CURRENT_BRANCH: ${getCurrentBranch()}")
    println("-> COMMIT_COUNT: ${getGitCommitsCount()}")
    println("-> DEV_VERSION_CODE: ${getDevelopVersionCode()}")
    println("-> LAST_MASTER_GIT_TAG: ${getLastMasterGitTagVersion()}")
    println("-> PROD_VERSION_CODE: ${getProductionVersionCode()}\n")
    println("----------------------------------\n")
}

fun getAutoVersionCode(): Int {
    val branch = getCurrentBranch()
    return if (branch == "master") getProductionVersionCode() else getDevelopVersionCode()
}

fun getAutoVersionName(): String {
    val (major, minor, patch, _, sha) = getLastMasterGitTagVersion()
    val code = getAutoVersionCode()
    val date = getBuildDate()
    return if (getCurrentBranch() == "master") "$major.$minor.$patch" else "$sha-$date ($code)"
}

fun getDevelopVersionCode(): Int {
    val count = getGitCommitsCount()
    return count.toIntOrNull() ?: 0
}

fun getProductionVersionCode(): Int {
    val (major, minor, patch) = getLastMasterGitTagVersion()
    return major.toInt() * 1_000_000 + minor.toInt() * 1_000 + patch.toInt()
}

fun getCurrentBranch(): String =
    executeCommand("git rev-parse --abbrev-ref HEAD")

fun getBuildDate(): String {
    val df = SimpleDateFormat("dd.MM.yyyy")
    df.timeZone = TimeZone.getTimeZone("UTC")
    return df.format(Date())
}

fun getGitCommitsCount(): String {
    return executeCommand("git rev-list ${getCurrentBranch()} --count")
}

fun getLastMasterGitTagVersion(): List<String> {
    val name = executeCommand("git describe --tags ${getCurrentBranch()} --long")
        .replace("v", "")
        .trim()

    val (tag, build, sha) = name.split('-')
    val (major, minor, patch) = tag.split('.')
    return listOf(major, minor, patch, build, sha)
}

fun getReleaseNotes(): String {
    val commit = getLatestSavedCommit()
    val gitLog = if (commit.isEmpty())
        "git log develop --no-merges --date=short --pretty=format:\"%h %ad %an: %s\" -10"
    else
        "git log develop --no-merges --date=short --pretty=format:\"%h %ad %an: %s\" $commit...HEAD"

    var releaseNotes = executeCommand(gitLog)

    if (releaseNotes.isEmpty()) {
        releaseNotes = getReleaseNotesFile().readText().trim()
    }

    return releaseNotes
}

fun getLatestSavedCommit(): String {
    val text = getReleaseNotesFile().readLines()
    if (text.isEmpty()) {
        return ""
    }

    // Get first word from release notes (commit hash).
    return text[0].split(" ")[0].trim()
}

fun getReleaseNotesFile() = File("settings/distribute/release-notes.txt")

fun downloadReleaseNotes(token: String?) {
    val url = URL("https://api.github.com/repos/JakubMosakowski/ItemistEvolved/actions/artifacts")
    val connection = (url.openConnection() as HttpURLConnection).apply {
        requestMethod = "GET"
    }
    val response = BufferedReader(InputStreamReader(connection.inputStream)).use { it.readText() }
    val resultObject = JSONObject(response)
    val artifactObject = resultObject.getJSONArray("artifacts").toList().find {
        val artifact = it as JSONObject
        artifact.getString("name") == "release-notes.txt"
    } as JSONObject?

    if (artifactObject == null) {
        println("No artifact found.")
        return
    }

    val artifactUrl = artifactObject.getString("archive_download_url")
    val process = ProcessBuilder(
        "curl",
        "-v",
        "-L",
        "-o",
        "settings/distribute/releaseNotes.zip",
        artifactUrl,
        "--header",
        "authorization: Bearer $token",
        "--header",
        "content-type: application/json"
    )
        .start()
    process.waitFor()

    println("Error (if happened) during fetching artifact: ${process.errorStream.bufferedReader().readText()}\n")

    executeCommand("unzip -o -a settings/distribute/releaseNotes.zip -d settings/distribute")
}

// Utility function to execute shell commands
fun executeCommand(command: String): String {
    val parts = command.split("\\s".toRegex())
    val process = ProcessBuilder(*parts.toTypedArray())
        .redirectErrorStream(true)
        .start()
    return process.inputStream.bufferedReader().readText().trim()
}
