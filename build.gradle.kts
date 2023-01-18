/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [build.gradle.kts] created by Ji Sungbin on 22. 12. 7. 오후 9:33
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

@file:Suppress("DSL_SCOPE_VIOLATION", "PropertyName")

import java.util.Locale
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
    alias(libs.plugins.code.ktlint)
    alias(libs.plugins.code.detekt)
    alias(libs.plugins.kotlin.kover)
    alias(libs.plugins.local.convention.enum)
    alias(libs.plugins.util.dependency.handler.extensions)
}

koverMerged {
    enable()
    xmlReport {
        reportFile.set(file("$rootDir/report/test-coverage/report.xml"))
    }
    htmlReport {
        reportDir.set(file("$rootDir/report/test-coverage/html"))
    }
}

buildscript {
    repositories {
        google()
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(libs.kotlin.core)
        classpath(libs.build.gradle.agp)
        // classpath(libs.build.google.service)
        // classpath(libs.build.firebase.crashlytics)
        // classpath(libs.build.firebase.performance)
        classpath(libs.build.androidx.navigation.safeargs)
        classpath(libs.build.ui.oss.license)
        classpath(libs.build.di.hilt)
    }
}

allprojects {
    apply {
        plugin(rootProject.libs.plugins.kotlin.kover.get().pluginId)
        plugin(rootProject.libs.plugins.code.ktlint.get().pluginId)
        plugin(rootProject.libs.plugins.code.detekt.get().pluginId)
        plugin(rootProject.libs.plugins.local.convention.enum.get().pluginId)
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
                jvmTarget = "17"
                freeCompilerArgs = freeCompilerArgs + listOf(
                    "-opt-in=kotlin.OptIn",
                    "-opt-in=kotlin.RequiresOptIn",
                )
            }
        }
    }
}

subprojects {
    // https://github.com/gradle/gradle/issues/4823#issuecomment-715615422
    @Suppress("UnstableApiUsage")
    if (
        gradle.startParameter.isConfigureOnDemand &&
        buildscript.sourceFile?.extension?.lowercase(Locale.getDefault()) == "kts" &&
        parent != rootProject
    ) {
        generateSequence(parent) { project ->
            project.parent.takeIf { parent ->
                parent != rootProject
            }
        }.forEach { project ->
            evaluationDependsOn(project.path)
        }
    }

    configure<KtlintExtension> {
        version.set(rootProject.libs.versions.plugin.code.ktlint.source.get())
        android.set(true)
        outputToConsole.set(true)
        additionalEditorconfigFile.set(file("$rootDir/.editorconfig"))
    }
}

tasks.register(
    name = "cleanAll",
    type = Delete::class,
) {
    allprojects.map(Project::getBuildDir).forEach(::delete)
}
