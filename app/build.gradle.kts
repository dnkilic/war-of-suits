plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = "com.dnkilic.warofsuits"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.compileSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":base-ui-components"))
    implementation(project(":feature-player"))
    implementation(project(":feature-game"))
    implementation(project(":feature-result"))

    implementation(Dependencies.appcompat)
    implementation(Dependencies.composePreviewTooling)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.hiltAndroid)
    implementation(Dependencies.lifecycleViewModel)
    implementation(Dependencies.accompanistSystemUiController)
    implementation(Dependencies.accompanistNavigationMaterial)
    implementation(Dependencies.composeActivity)
    implementation(Dependencies.composeNavigation)
    debugImplementation(Dependencies.composeUiTooling)

    kapt(Dependencies.hiltAndroidCompiler)
}