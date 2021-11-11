/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [build.gradle.kts] created by Ji Sungbin on 21. 6. 20. 오후 11:51.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.Essential.Gradle}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.Hilt}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Essential.Kotlin}")
        classpath("com.google.android.gms:oss-licenses-plugin:${Versions.OssLicense.Classpath}")
        classpath("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:${Versions.Essential.Ksp}")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    afterEvaluate {
        tasks.withType<KotlinCompile> {
            kotlinOptions.freeCompilerArgs += listOf(
                "-Xopt-in=kotlin.RequiresOptIn",
                "-Xopt-in=kotlin.OptIn"
            )
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
