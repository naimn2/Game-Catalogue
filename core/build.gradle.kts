plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    id("kotlin-parcelize")
    alias(libs.plugins.hiltAndroid)
}

android {
    namespace = "com.muflihun.gamecatalogue.core"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(
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
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    api(libs.androidx.constraintlayout)
    api(libs.google.material)
    testApi(libs.junit)
    androidTestApi(libs.androidx.junit)
    androidTestApi(libs.androidx.espresso.core)

    api(libs.recyclerview)
    api(libs.material)
    api(libs.glide)

    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    androidTestImplementation(libs.room.testing)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    api(libs.androidx.navigation.fragment)
    api(libs.androidx.navigation.ui)
    api(libs.androidx.navigation.dynamic)
    api(libs.androidx.appcompat)
    api(libs.material)
    api(libs.androidx.constraintlayout)

    api(libs.androidx.activity.ktx)
    api(libs.androidx.fragment.ktx)

    api(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    androidTestApi(libs.androidx.junit.ktx)

    debugApi(libs.square.leakcanary)

    implementation(libs.android.database.sqlcipher)
    implementation(libs.androidx.sqlite.ktx)
}