plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.bugbender.memepick"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bugbender.memepick"
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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    //modules
    implementation(project(":core:presentation"))
    implementation(project(":core:theme"))
    implementation(project(":core:data"))
    implementation(project(":core:firebase"))

    implementation(project(":features:memes"))
    implementation(project(":data:memes-api"))
    implementation(project(":data:memes-imp"))

    implementation(project(":features:favorites"))
    implementation(project(":data:favorites-api"))
    implementation(project(":data:favorites-imp"))

    implementation(project(":features:authentication"))

    implementation(project(":features:profile"))

    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    //base libs
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}