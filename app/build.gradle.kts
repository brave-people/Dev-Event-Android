/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [build.gradle.kts] created by Ji Sungbin on 23. 1. 2. 오후 5:32
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

@file:Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")

import DependencyHandler.Extensions.implementations

plugins {
    id(ConventionEnum.AndroidApplication)
    id(ConventionEnum.AndroidHilt)
    // id(libs.plugins.gms.google.service.get().pluginId)
    // id(libs.plugins.firebase.crashlytics.get().pluginId)
    // id(libs.plugins.firebase.performance.get().pluginId)
}

android {
    namespace = "team.brave.devevent.android"

    buildFeatures {
        dataBinding = true
    }

    lint {
        // Error: When targeting Android 13 or higher, posting a permission requires holding the POST_NOTIFICATIONS permission (usage from leakcanary.NotificationEventListener)
        disable.add("NotificationPermission")
    }
}

dependencies {
    implementations(
        /*platform(libs.firebase.bom),
        libs.analytics.anrwatchdog,
        libs.firebase.performance,
        libs.firebase.analytics,
        libs.firebase.crashlytics,*/
        projects.presentation, // for launch IntroActivity
    )
    debugImplementation(libs.analytics.leakcanary)
}
