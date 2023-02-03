/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [build.gradle.kts] created by Ji Sungbin on 22. 12. 7. 오후 9:30
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

@file:Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")

import DependencyHandler.Extensions.implementations

GradleInstallation.library(project) {
    namespace = "team.brave.devevent.android.presentation"

    sourceSets {
        getByName("debug").kotlin.srcDirs("build/generated/source/navigation-args/debug")
        getByName("release").kotlin.srcDirs("build/generated/source/navigation-args/release")
    }

    buildFeatures {
        dataBinding = true
        buildConfig = true
    }
}

GradleInstallation.hilt(project)
GradleInstallation.junit(project)

plugins {
    id(libs.plugins.androidx.navigation.safeargs.get().pluginId)
}

dependencies {
    implementations(
        platform(libs.firebase.bom),
        libs.firebase.crashlytics,
        libs.androidx.appcompat,
        libs.androidx.navigation.ktx.ui,
        libs.androidx.navigation.ktx.fragment,
        libs.ui.material3,
        libs.ui.oss.license,
        libs.ui.glide.core,
        libs.ui.glide.recyclerview,
        libs.ktx.activiy,
        libs.ktx.fragment,
        libs.ktx.lifecycle.runtime,
        libs.ktx.lifecycle.viewmodel,
        projects.di,
        projects.domain,
        projects.datastore,
    )
    androidTestImplementation(libs.test.androidx.navigation)
}
