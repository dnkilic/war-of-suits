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

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":base-ui-components"))
    implementation(project(":base-data"))

    implementation(Dependencies.appcompat)
    implementation(Dependencies.composePreviewTooling)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.hiltAndroid)
    implementation(Dependencies.lifecycleViewModel)
    implementation(Dependencies.constraintLayoutCompose)
    implementation(Dependencies.accompanistSystemUiController)
    debugImplementation(Dependencies.composeUiTooling)

    kapt(Dependencies.hiltAndroidCompiler)

    testImplementation(Dependencies.jUnit)
    testImplementation(Dependencies.mockk)
    testImplementation(Dependencies.coroutinesTest)
    testImplementation(Dependencies.coreTesting)

    androidTestImplementation(Dependencies.composeJUnit)
    debugImplementation(Dependencies.composeUiTest)
}