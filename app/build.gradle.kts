plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.23"
}

android {
    namespace = "com.example.myapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.myapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("debug")
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
        buildConfig = true
    }

    flavorDimensions += "source"

    productFlavors {
        create("newsapi") {
            dimension = "source"
            buildConfigField ("String", "NEWS_API_KEY", "\"6444c6d8fdda4861aa9609eb13fe74b8\"")
            buildConfigField ("String", "URL", "\"https://newsapi.org/\"")
            buildConfigField ("String", "COUNTRY", "\"us\"")
        }
        create("newyorktimes") {
            dimension = "source"
            buildConfigField ("String", "URL", "\"https://api.nytimes.com/svc/archive/\"")
            buildConfigField ("String", "NEWS_API_KEY", "\"4c32rW8zG8XNgmkG1bO62Kkz6MkI4ghi\"")
            buildConfigField("String", "COUNTRY", "\"\"")
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.appcompat)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation (libs.retrofit)
    implementation(libs.converter.gson)

    implementation(libs.kotlinx.serialization.json)

    implementation (libs.logging.interceptor)

    implementation(platform(libs.androidx.compose.bom.v20250200))
    implementation(libs.coil.compose)

    implementation(libs.androidx.biometric)

    implementation(libs.kotlinx.serialization.json.v160)
    implementation(libs.retrofit2.kotlinx.serialization.converter)

}