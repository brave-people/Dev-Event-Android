/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [build.gradle.kts] created by Ji Sungbin on 22. 12. 7. 오후 9:30
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

@file:Suppress(
    "UnstableApiUsage",
    "DSL_SCOPE_VIOLATION",
)

import DependencyHandler.Extensions.implementations

plugins {
    id(ConventionEnum.AndroidLibrary)
    id(ConventionEnum.AndroidHilt)
    id(ConventionEnum.JvmJUnit4)
    id(libs.plugins.androidx.navigation.safeargs.get().pluginId)
}

android {
    namespace = "team.brave.devevent.android.presentation"

    sourceSets {
        getByName("main").java.srcDirs("build/generated/source/navigation-args")
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementations(
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
    )
    androidTestImplementation(libs.test.androidx.navigation)
}
