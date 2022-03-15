object Versions {
    const val kotlin = "1.6.10"
    const val compose = "1.1.1"
    const val accompanist = "0.23.1"
    const val hilt = "2.39.1"
    const val jUnit = "4.13.2"
    const val lifecycle = "2.4.1"
    const val constraintLayoutCompose = "1.0.0"
    const val coroutines = "1.3.9"
    const val appcompat = "1.4.1"
}

object AppConfig {
    const val compileSdk = 31
    const val targetSdk = 31
    const val minSdk = 21
}

object Dependencies {
    val kotlin by lazy {
        "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    }
    val coroutines by lazy {
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }
    val jUnit by lazy {
        "junit:junit:${Versions.jUnit}"
    }
    val composeUiTooling by lazy {
        "androidx.compose.ui:ui-tooling:${Versions.compose}"
    }
    val composePreviewTooling by lazy {
        "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    }
    val composeMaterial by lazy {
        "androidx.compose.material:material:${Versions.compose}"
    }
    val hiltAndroid by lazy {
        "com.google.dagger:hilt-android:${Versions.hilt}"
    }
    val hiltAndroidCompiler by lazy {
        "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    }
    val lifecycleViewModel by lazy {
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    }
    val constraintLayoutCompose by lazy {
        "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayoutCompose}"
    }
    val accompanistSystemUiController by lazy {
        "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"
    }
    val accompanistNavigationMaterial by lazy {
        "com.google.accompanist:accompanist-navigation-material:${Versions.accompanist}"
    }
    val appcompat by lazy {
        "androidx.appcompat:appcompat:${Versions.appcompat}"
    }
}