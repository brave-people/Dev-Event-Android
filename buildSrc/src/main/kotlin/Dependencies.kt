/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [Dependencies.kt] created by Ji Sungbin on 21. 6. 20. 오후 11:52.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

import org.gradle.api.JavaVersion

object Versions {
    const val Orbit = "4.3.1"

    object Essential {
        const val Kotlin = "1.6.0"
        const val Gradle = "7.1.1"
        const val CoreKtx = "1.7.0"
        const val Coroutines = "1.6.0"
        const val Ksp = "1.6.10-1.0.2"
    }

    object Ui {
        const val Browser = "1.4.0"
        const val Material = "1.4.0"
        const val Splash = "1.0.0-beta01"
    }

    object Compose {
        const val Lottie = "4.2.2"
        const val Activity = "1.4.0"
        const val Master = "1.1.0-rc03"
        const val Landscapist = "1.4.5"
        const val Material = "1.1.0-rc01"
        const val ConstraintLayout = "1.0.0"
        const val LifecycleViewModel = "2.4.0"
    }

    object OssLicense {
        const val Master = "17.0.0"
        const val Classpath = "0.10.4"
    }

    object Network {
        const val OkHttp = "4.9.3"
        const val Retrofit = "2.9.0"
    }

    object Jetpack {
        const val Room = "2.4.1"
        const val Hilt = "2.40.5"
    }

    object Util {
        const val Erratum = "1.0.1"
        const val Logeukes = "1.0.1"
        const val LeakCanary = "2.8"
        const val CheckDependencyUpdates = "1.5.0"
    }
}

object Dependencies {
    const val Orbit = "org.orbit-mvi:orbit-viewmodel:${Versions.Orbit}"

    val Essential = listOf(
        "androidx.core:core-ktx:${Versions.Essential.CoreKtx}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Essential.Coroutines}"
    )

    val Ui = listOf(
        "androidx.browser:browser:${Versions.Ui.Browser}",
        "androidx.core:core-splashscreen:${Versions.Ui.Splash}",
        "com.google.android.material:material:${Versions.Ui.Material}",
        "com.google.android.gms:play-services-oss-licenses:${Versions.OssLicense.Master}"
    )

    val Compose = listOf(
        "androidx.compose.ui:ui-tooling:${Versions.Compose.Master}",
        "com.airbnb.android:lottie-compose:${Versions.Compose.Lottie}",
        "androidx.activity:activity-compose:${Versions.Compose.Activity}",
        "androidx.compose.material:material:${Versions.Compose.Material}",
        "com.github.skydoves:landscapist-glide:${Versions.Compose.Landscapist}",
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.Compose.LifecycleViewModel}",
        "androidx.constraintlayout:constraintlayout-compose:${Versions.Compose.ConstraintLayout}"
    )

    val Network = listOf(
        "com.squareup.retrofit2:retrofit:${Versions.Network.Retrofit}",
        "com.squareup.okhttp3:logging-interceptor:${Versions.Network.OkHttp}"
    )

    val Util = listOf(
        "io.github.jisungbin:erratum:${Versions.Util.Erratum}",
        "io.github.jisungbin:logeukes:${Versions.Util.Logeukes}",
    )

    val Debug = listOf(
        "com.squareup.leakcanary:leakcanary-android:${Versions.Util.LeakCanary}"
    )

    object Jetpack {
        const val Room = "androidx.room:room-compiler:${Versions.Jetpack.Room}"
        const val Hilt = "com.google.dagger:hilt-android:${Versions.Jetpack.Hilt}"
    }

    object Compiler {
        const val RoomKsp = "androidx.room:room-compiler:${Versions.Jetpack.Room}"
        const val Hilt = "com.google.dagger:hilt-android-compiler:${Versions.Jetpack.Hilt}"
    }
}
