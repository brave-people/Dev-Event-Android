/*
 * Designed and developed by Duckie Team, 2022
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/duckie-android/blob/develop/LICENSE
 */

@file:Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")

import DependencyHandler.Extensions.implementations

plugins {
    id(ConventionEnum.AndroidApplication)
    id(ConventionEnum.AndroidHilt)
    id(libs.plugins.gms.google.service.get().pluginId)
    id(libs.plugins.firebase.crashlytics.get().pluginId)
    id(libs.plugins.firebase.performance.get().pluginId)
}

android {
    namespace = "team.brave.devevent.android"

    lint {
        // Error: When targeting Android 13 or higher, posting a permission requires holding the POST_NOTIFICATIONS permission (usage from leakcanary.NotificationEventListener)
        disable.add("NotificationPermission")
    }
}

dependencies {
    implementations(
        platform(libs.firebase.bom),
        libs.analytics.anrwatchdog,
        libs.firebase.performance,
        libs.firebase.analytics,
        libs.firebase.crashlytics,
        projects.presentation, // for launch IntroActivity
    )
    debugImplementation(libs.analytics.leakcanary)
}
