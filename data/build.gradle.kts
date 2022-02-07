/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [build.gradle.kts] created by Ji Sungbin on 22. 1. 7. 오전 12:14
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
}

android {
    kapt {
        correctErrorTypes = true
        arguments {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(Dependencies.Jetpack.Hilt)
    implementation(Dependencies.Jetpack.Room)

    Dependencies.Network.forEach(::implementation)
    Dependencies.Essential.forEach(::implementation)

    kapt(Dependencies.Compiler.Hilt)
    ksp(Dependencies.Compiler.RoomKsp)
}
