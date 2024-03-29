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

plugins {
    `android-library`
    `kotlin-android`
}

GradleInstallation.with(project) {
    library {
        namespace = "team.brave.devevent.android.presentation"

        buildFeatures {
            dataBinding = true
            buildConfig = true
        }
    }
    hilt()
    junit()
}

dependencies {
    implementations(
        platform(libs.firebase.bom),
        libs.firebase.crashlytics,
        libs.androidx.appcompat,
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
}
