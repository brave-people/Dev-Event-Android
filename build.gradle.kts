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
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Essential.Kotlin}")
        // classpath("org.jetbrains.kotlin:kotlin-serialization:${Versions.Essential.Kotlin}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.Hilt.Master}")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
        maven { setUrl("https://oss.jfrog.org/libs-snapshot") }
        maven { setUrl("https://oss.sonatype.org/content/repositories/snapshots") }
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
