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
    id(ConventionEnum.JvmJUnit4)
}

android {
    namespace = "team.brave.devevent.android.presentation"
}

dependencies {
    implementations(
        libs.androidx.splash,
        libs.androidx.appcompat,
        libs.ui.material3,
        libs.ui.oss.license,
    )
}
