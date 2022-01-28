/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [Dependencies.kt] created by Ji Sungbin on 21. 6. 20. 오후 11:52.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

import org.gradle.api.JavaVersion

object Application {
    const val minSdk = 24
    const val targetSdk = 31
    const val compileSdk = 31
    const val jvmTarget = "11"
    const val versionCode = 14
    const val versionName = "1.1.1"

    val targetCompat = JavaVersion.VERSION_11
    val sourceCompat = JavaVersion.VERSION_11
}

object Versions {
    const val Orbit = "4.3.0"

    object Essential {
        const val Kotlin = "1.6.0"
        const val CoreKtx = "1.7.0"
        const val Coroutines = "1.6.0"
        const val Gradle = "7.1.0"
    }

    object Network {
        const val OkHttp = "4.9.3"
        const val Retrofit = "2.9.0"
    }

    object Ui {
        const val Browser = "1.4.0"
        const val Material = "1.4.0"
    }

    object Jetpack {
        const val Hilt = "2.40.5"
        const val Room = "2.4.0"
    }

    object Util {
        const val Erratum = "1.0.1"
        const val Logeukes = "1.0.1"
        const val LeakCanary = "2.8"
        const val CheckDependencyUpdates = "1.5.0"
    }

    object Compose {
        const val Activity = "1.4.0"
        const val Master = "1.1.0-rc01"
        const val LandscapistCoil = "1.4.5"
        const val ConstraintLayout = "1.0.0-beta01"
    }

    object OssLicense {
        const val Master = "17.0.0"
        const val Classpath = "0.10.4"
    }
}

object Dependencies {
    const val Orbit = "org.orbit-mvi:orbit-viewmodel:${Versions.Orbit}"
    const val LandscapistCoil =
        "com.github.skydoves:landscapist-coil:${Versions.Compose.LandscapistCoil}"

    object Compiler {
        const val Room = "androidx.room:room-compiler:${Versions.Jetpack.Room}"
        const val Hilt = "com.google.dagger:hilt-android-compiler:${Versions.Jetpack.Hilt}"
    }

    val Essential = listOf(
        "androidx.core:core-ktx:${Versions.Essential.CoreKtx}",
        "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Essential.Kotlin}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Essential.Coroutines}"
    )

    val Network = listOf(
        "com.squareup.retrofit2:retrofit:${Versions.Network.Retrofit}",
        "com.squareup.okhttp3:logging-interceptor:${Versions.Network.OkHttp}"
    )

    val Ui = listOf(
        "androidx.browser:browser:${Versions.Ui.Browser}",
        "com.google.android.material:material:${Versions.Ui.Material}",
        "com.google.android.gms:play-services-oss-licenses:${Versions.OssLicense.Master}"
    )

    val Util = listOf(
        "io.github.jisungbin:erratum:${Versions.Util.Erratum}",
        "io.github.jisungbin:logeukes:${Versions.Util.Logeukes}",
    )

    val Debug = listOf(
        "com.squareup.leakcanary:leakcanary-android:${Versions.Util.LeakCanary}"
    )

    val Compose = listOf(
        "androidx.compose.ui:ui:${Versions.Compose.Master}",
        "androidx.compose.compiler:compiler:${Versions.Compose.Master}",
        "androidx.compose.material:material:${Versions.Compose.Master}",
        "androidx.activity:activity-compose:${Versions.Compose.Activity}",
        "androidx.constraintlayout:constraintlayout-compose:${Versions.Compose.ConstraintLayout}"
    )

    object Jetpack {
        const val Room = "androidx.room:room-runtime:${Versions.Jetpack.Room}"
        const val Hilt = "com.google.dagger:hilt-android:${Versions.Jetpack.Hilt}"
    }
}
