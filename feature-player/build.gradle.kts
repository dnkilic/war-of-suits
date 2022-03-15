plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.compileSdk

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions.jvmTarget = "1.8"
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion  = Versions.compose
    }
}

dependencies {
    implementation(project(":base-ui-components"))

    implementation(Dependencies.composePreviewTooling)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.hiltAndroid)
    implementation(Dependencies.lifecycleViewModel)
    debugImplementation(Dependencies.composeUiTooling)

    kapt(Dependencies.hiltAndroidCompiler)

    testImplementation(Dependencies.jUnit)
}