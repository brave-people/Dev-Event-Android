/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [build.gradle.kts] created by Ji Sungbin on 23. 1. 19. 오전 1:33
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.kotlin.core)
    implementation(libs.build.gradle.agp)
    implementation("com.squareup:javapoet:1.13.0") {
        because("https://github.com/google/dagger/issues/3068#issuecomment-999118496")
    }
}
