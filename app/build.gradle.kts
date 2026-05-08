plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)

    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.crocusoft_mova"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.crocusoft_mova"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val apiKey = project.findProperty("API_KEY")?.toString() ?: ""
        buildConfigField("String", "API_KEY", "\"$apiKey\"")
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
    buildFeatures {
        compose = true
        buildConfig = true
    }
    androidResources {
        generateLocaleConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.firebase.dataconnect)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)


    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.lifecycle.viewmodel.compose.v280)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation("androidx.compose.material3:material3:1.2.1")
    implementation("com.google.android.material:material:1.12.0")
    //dataStore
    implementation(libs.androidx.datastore.preferences)


    // Navigation
    implementation(libs.androidx.navigation.compose)


    // Coil
    implementation(libs.coil3.coil.compose)
    implementation(libs.coil.network.okhttp)

    implementation(libs.jetbrains.kotlinx.serialization.json)


    // Ktor
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)

    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.jetbrains.kotlinx.serialization.json)

    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.logging)

    // firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.storage)
    implementation(libs.firebase.firestore)

    // DotEnv

    implementation(libs.dotenv.kotlin)

}
