plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "igor.second.spaceapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "igor.second.spaceapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 3
        versionName = "3.0"

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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    // google font
    implementation (libs.androidx.ui.text)
    implementation (libs.androidx.ui.text.google.fonts)
    // billing
    implementation (libs.billing)
    implementation (libs.billing.ktx)
    // DB
    implementation (libs.androidx.datastore.preferences)
    // navigation
    implementation(libs.androidx.navigation.compose)
    // location Services
    implementation (libs.accompanist.permissions)
    implementation (libs.play.services.location)
    implementation (libs.kotlinx.coroutines.play.services)
    // Retrofit
    implementation (libs.retrofit2.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.gson)
    // OkHttp
    implementation (libs.okhttp)
    implementation (libs.logging.interceptor)
    // WorkManager
    implementation (libs.androidx.work.runtime.ktx)

    implementation (libs.material3)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}