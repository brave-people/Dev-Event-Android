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
    const val targetSdk = 30
    const val compileSdk = 30
    const val jvmTarget = "1.8"
    const val versionCode = 12
    const val versionName = "1.0.7"

    val targetCompat = JavaVersion.VERSION_11
    val sourceCompat = JavaVersion.VERSION_11
}

object Versions {
    const val Orbit = "4.2.0"

    object Essential {
        const val Ksp = "1.5.31-1.0.0"
        const val Kotlin = "1.5.31"
        const val Gradle = "7.1.0-beta03"
        const val CoreKtx = "1.7.0"
        const val Coroutines = "1.5.1"
    }

    object Network {
        const val OkHttp = "4.9.2"
        const val Retrofit = "2.9.0"
    }

    object Ui {
        const val Browser = "1.4.0"
    }

    object Util {
        const val Erratum = "1.0.1"
        const val Logeukes = "1.0.1"
        const val LeakCanary = "2.7"
        const val CheckDependencyUpdates = "1.5.0"
    }

    object Jetpack {
        const val Hilt = "2.40"
        const val Room = "2.3.0"
    }

    object Compose {
        const val Lottie = "4.2.0"
        const val Master = "1.0.5"
        const val Activity = "1.4.0"
        const val FancyBottomBar = "1.0.1"
        const val LandscapistCoil = "1.4.2"
        const val SwipeToRefresh = "0.20.2"
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

    val Essential = listOf(
        "androidx.core:core-ktx:${Versions.Essential.CoreKtx}",
        "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Essential.Kotlin}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Essential.Coroutines}"
    )

    val Retrofit = listOf(
        "com.squareup.retrofit2:retrofit:${Versions.Network.Retrofit}",
        "com.squareup.okhttp3:logging-interceptor:${Versions.Network.OkHttp}"
    )

    val Ui = listOf(
        "androidx.browser:browser:${Versions.Ui.Browser}",
        "com.google.android.gms:play-services-oss-licenses:${Versions.OssLicense.Master}"
    )

    val Jetpack = listOf(
        "androidx.room:room-ktx:${Versions.Jetpack.Room}",
        "androidx.room:room-runtime:${Versions.Jetpack.Room}",
        "com.google.dagger:hilt-android:${Versions.Jetpack.Hilt}"
    )

    val Util = listOf(
        "io.github.jisungbin:erratum:${Versions.Util.Erratum}",
        "io.github.jisungbin:logeukes:${Versions.Util.Logeukes}",
        "com.squareup.leakcanary:leakcanary-android:${Versions.Util.LeakCanary}"
    )

    val Compose = listOf(
        "androidx.compose.ui:ui:${Versions.Compose.Master}",
        "androidx.compose.ui:ui-tooling:${Versions.Compose.Master}",
        "com.airbnb.android:lottie-compose:${Versions.Compose.Lottie}",
        "androidx.compose.compiler:compiler:${Versions.Compose.Master}",
        "androidx.compose.material:material:${Versions.Compose.Master}",
        "androidx.activity:activity-compose:${Versions.Compose.Activity}",
        "io.github.jisungbin:fancybottombar:${Versions.Compose.FancyBottomBar}",
        "com.google.accompanist:accompanist-swiperefresh:${Versions.Compose.SwipeToRefresh}",
        "androidx.constraintlayout:constraintlayout-compose:${Versions.Compose.ConstraintLayout}"
    )

    val Compiler = listOf(
        "androidx.room:room-compiler:${Versions.Jetpack.Room}",
        "com.google.dagger:hilt-android-compiler:${Versions.Jetpack.Hilt}"
    )
}
