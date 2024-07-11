import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.services)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.nav.safeargs)
    alias(libs.plugins.hilt)
    id("com.google.devtools.ksp")

}

android {
    namespace = "com.the_news"
    compileSdk = 34

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.the_news"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        create("staging") {

        }
        create("development") {

        }


    }

    flavorDimensions += listOf("releaseType")
    productFlavors {
        create("prod") {
            dimension = "releaseType"

            val apiKey =
                gradleLocalProperties(rootDir, rootProject.providers).getProperty("API_KEY")
            val apiURL = gradleLocalProperties(rootDir, rootProject.providers).getProperty("MY_URL")
            buildConfigField("String", "API_KEY", "\"$apiKey\"")
            buildConfigField("String", "API_URL", "\"$apiURL\"")
        }
        create("dev") {
            dimension = "releaseType"
            applicationIdSuffix = ".dev"
            val apiKey =
                gradleLocalProperties(rootDir, rootProject.providers).getProperty("API_KEY_DEV")
            val apiURL =
                gradleLocalProperties(rootDir, rootProject.providers).getProperty("MY_URL_DEV")
            buildConfigField("String", "API_KEY", "\"$apiKey\"")
            buildConfigField("String", "API_URL", "\"$apiURL\"")
        }

        create("stg") {
            dimension = "releaseType"
            applicationIdSuffix = ".stg"
            val apiKey =
                gradleLocalProperties(rootDir, rootProject.providers).getProperty("API_KEY_STG")
            val apiURL =
                gradleLocalProperties(rootDir, rootProject.providers).getProperty("MY_URL_STG")
            buildConfigField("String", "API_KEY", "\"$apiKey\"")
            buildConfigField("String", "API_URL", "\"$apiURL\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }


}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.gson.converter)
    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.okHttp)
    implementation(libs.lifeCyle.liveData)
    implementation(libs.lifeCyle.viwModel)
    implementation(libs.hilt)
    implementation(libs.glide)
    implementation(platform(libs.firebase.bom))
    ksp(libs.hilt.compiler)
    ksp(libs.room.compiler)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    testImplementation(libs.room.testing)
    androidTestImplementation(libs.mockito.android)
    testImplementation(libs.mockito.core)
    implementation(libs.nav.fragment)
    implementation(libs.nav.ui)
    testImplementation(libs.junit)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mock.web.server)
    testImplementation(libs.truth)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}