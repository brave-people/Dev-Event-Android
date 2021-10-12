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
    object Essential {
        const val Kotlin = "1.5.30"
        const val CoreKtx = "1.6.0"
        const val Coroutines = "1.5.2"
        const val Gradle = "7.1.0-alpha05"
    }

    object Ui {
        const val Browser = "1.3.0"
        const val FancyBottomBar = "1.0.1"
        const val SwipeToRefresh = "0.19.0"
        const val LandscapistCoil = "1.3.8"
        const val ConstraintLayout = "1.0.0-beta01"
    }

    object Util {
        const val Logcat = "0.1"
        const val Erratum = "1.0.1"
        const val CheckDependencyUpdates = "1.5.0"
    }

    object Network {
        const val OkHttp = "4.9.2"
        const val Retrofit = "2.9.0"
    }

    object Jetpack {
        const val Room = "2.3.0"
        const val Hilt = "2.39.1"
    }

    object Compose {
        const val Master = "1.0.3"
        const val Activity = "1.3.1"
    }

    object Debug {
        const val LeakCanary = "2.7"
    }

    object OssLicense {
        const val Master = "17.0.0"
        const val Classpath = "0.10.4"
    }
}

object Dependencies {
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.Debug.LeakCanary}"

    const val LandscapistCoil =
        "com.github.skydoves:landscapist-coil:${Versions.Ui.LandscapistCoil}"

    val util = listOf(
        "com.squareup.logcat:logcat:${Versions.Util.Logcat}",
        "io.github.jisungbin:erratum:${Versions.Util.Erratum}"
    )

    val network = listOf(
        "com.squareup.retrofit2:retrofit:${Versions.Network.Retrofit}",
        "com.squareup.okhttp3:logging-interceptor:${Versions.Network.OkHttp}",
    )

    val essential = listOf(
        "androidx.core:core-ktx:${Versions.Essential.CoreKtx}",
        "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Essential.Kotlin}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Essential.Coroutines}"
    )

    val ui = listOf(
        "androidx.browser:browser:${Versions.Ui.Browser}",
        "io.github.jisungbin:fancybottombar:${Versions.Ui.FancyBottomBar}",
        "com.google.accompanist:accompanist-swiperefresh:${Versions.Ui.SwipeToRefresh}",
        "com.google.android.gms:play-services-oss-licenses:${Versions.OssLicense.Master}",
        "androidx.constraintlayout:constraintlayout-compose:${Versions.Ui.ConstraintLayout}"
    )

    val jetpack = listOf(
        "androidx.room:room-ktx:${Versions.Jetpack.Room}",
        "androidx.room:room-runtime:${Versions.Jetpack.Room}",
        "com.google.dagger:hilt-android:${Versions.Jetpack.Hilt}"
    )

    val compose = listOf(
        "androidx.compose.ui:ui:${Versions.Compose.Master}",
        "androidx.compose.ui:ui-tooling:${Versions.Compose.Master}",
        "androidx.compose.compiler:compiler:${Versions.Compose.Master}",
        "androidx.compose.material:material:${Versions.Compose.Master}",
        "androidx.activity:activity-compose:${Versions.Compose.Activity}",
        "androidx.compose.runtime:runtime-livedata:${Versions.Compose.Master}"
    )

    val compiler = listOf(
        "androidx.room:room-compiler:${Versions.Jetpack.Room}",
        "com.google.dagger:hilt-android-compiler:${Versions.Jetpack.Hilt}",
    )
}
