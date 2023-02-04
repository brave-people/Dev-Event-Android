/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [build.gradle.kts] created by Ji Sungbin on 22. 12. 7. 오후 9:33
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

@file:Suppress("DSL_SCOPE_VIOLATION")

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
    alias(libs.plugins.code.ktlint)
    alias(libs.plugins.code.detekt)
    alias(libs.plugins.util.dependency.handler.extensions)
}

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(libs.kotlin.core)
        classpath(libs.build.gradle.agp)
        classpath(libs.build.google.service)
        classpath(libs.build.firebase.crashlytics)
        classpath(libs.build.firebase.performance)
        classpath(libs.build.ui.oss.license)
        classpath(libs.build.di.hilt)
    }
}

allprojects {
    apply {
        plugin(rootProject.libs.plugins.code.ktlint.get().pluginId)
        plugin(rootProject.libs.plugins.code.detekt.get().pluginId)
        plugin(rootProject.libs.plugins.util.dependency.handler.extensions.get().pluginId)
    }

    repositories {
        google()
        mavenCentral()
    }

    afterEvaluate {
        detekt {
            parallel = true
            buildUponDefaultConfig = true
            toolVersion = libs.versions.plugin.code.detekt.get()
            config.setFrom(files("$rootDir/detekt-config.yml"))
        }

        tasks.withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = "11"
                freeCompilerArgs = freeCompilerArgs + listOf(
                    "-opt-in=kotlin.OptIn",
                    "-opt-in=kotlin.RequiresOptIn",
                    "-Xcontext-receivers",
                )
            }
        }

        tasks.withType<Detekt>().configureEach {
            exclude(project.buildDir.absolutePath)
        }

        tasks.withType<DetektCreateBaselineTask>().configureEach {
            exclude(project.buildDir.absolutePath)
        }
    }
}

subprojects {
    configure<KtlintExtension> {
        version.set(rootProject.libs.versions.plugin.code.ktlint.source.get())
        android.set(true)
        outputToConsole.set(true)
        additionalEditorconfigFile.set(file("$rootDir/.editorconfig"))
    }
}

tasks.register(name = "cleanAll", type = Delete::class) {
    allprojects.map(Project::getBuildDir).forEach(::delete)
}

// TODO: GitHub Actions 의 yml 로 변경
tasks.create("moveGoogleServiceFile") {
    val file = File("$rootDir/public-build-resource/google-services.json")
    val target = File("$rootDir/app/google-services.json")
    if (target.exists()) return@create
    file.copyTo(target)
}
