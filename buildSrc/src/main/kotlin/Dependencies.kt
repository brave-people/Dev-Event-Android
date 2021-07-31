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
    const val versionCode = 9
    const val versionName = "1.0.4"

    val targetCompat = JavaVersion.VERSION_11
    val sourceCompat = JavaVersion.VERSION_11
}

object Versions {
    object Essential {
        const val Kotlin = "1.5.10" // todo: 1.5.20
        const val Gradle = "7.1.0-alpha05"
        const val CoreKtx = "1.6.0"
        const val Coroutines = "1.5.1"
    }

    object Ui {
        const val SwipeToRefresh = "0.15.0"
        const val Browser = "1.3.0"
        const val ConstraintLayout = "1.0.0-beta01"
        const val FancyBottomBar = "1.0.1"
        const val LandscapistCoil = "1.3.0"
    }

    object Util {
        const val CrashReporter = "1.1.0"
        const val CheckDependencyUpdates = "1.4.1"
    }

    object Network {
        const val Retrofit = "2.9.0"
    }

    object Room {
        const val Master = "2.3.0"
        const val Jdbc = "3.36.0.1"
    }

    object Hilt {
        const val Master = "2.37"
    }

    object Compose {
        const val Master = "1.0.0"
        const val Activity = "1.3.0"
    }

    object Debug {
        const val LeakCanary = "2.7"
    }
}

object Dependencies {
    val debug = listOf(
        "com.squareup.leakcanary:leakcanary-android:${Versions.Debug.LeakCanary}"
    )

    val network = listOf(
        "com.squareup.retrofit2:retrofit:${Versions.Network.Retrofit}"
    )

    val essential = listOf(
        "androidx.core:core-ktx:${Versions.Essential.CoreKtx}",
        "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Essential.Kotlin}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Essential.Coroutines}"
    )

    val ui = listOf(
        "androidx.browser:browser:${Versions.Ui.Browser}",
        "androidx.constraintlayout:constraintlayout-compose:${Versions.Ui.ConstraintLayout}",
        "com.google.accompanist:accompanist-swiperefresh:${Versions.Ui.SwipeToRefresh}",
        "io.github.jisungbin:fancybottombar:${Versions.Ui.FancyBottomBar}",
        "com.github.skydoves:landscapist-coil:${Versions.Ui.LandscapistCoil}"
    )

    val util = listOf(
        "com.balsikandar.android:crashreporter:${Versions.Util.CrashReporter}",
        "com.squareup.retrofit2:converter-gson:${Versions.Network.Retrofit}"
    )

    val room = listOf(
        "androidx.room:room-runtime:${Versions.Room.Master}",
        "androidx.room:room-ktx:${Versions.Room.Master}"
    )

    val hilt = listOf(
        "com.google.dagger:hilt-android:${Versions.Hilt.Master}"
    )

    val compose = listOf(
        "androidx.activity:activity-compose:${Versions.Compose.Activity}",
        "androidx.compose.compiler:compiler:${Versions.Compose.Master}",
        "androidx.compose.runtime:runtime-livedata:${Versions.Compose.Master}",
        "androidx.compose.material:material:${Versions.Compose.Master}",
        "androidx.compose.ui:ui-tooling:${Versions.Compose.Master}",
        "androidx.compose.ui:ui:${Versions.Compose.Master}"
    )

    val compiler = listOf(
        "androidx.room:room-compiler:${Versions.Room.Master}",
        "com.google.dagger:hilt-android-compiler:${Versions.Hilt.Master}",
        "org.xerial:sqlite-jdbc:${Versions.Room.Jdbc}"
    )
}
