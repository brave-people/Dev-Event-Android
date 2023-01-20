/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [build.gradle.kts] created by Ji Sungbin on 23. 1. 2. 오후 5:32
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

import DependencyHandler.Extensions.implementations
import DependencyHandler.Extensions.testImplementations

plugins {
    id(ConventionEnum.AndroidLibrary)
    id(ConventionEnum.AndroidHilt)
    id(ConventionEnum.JvmJUnit4)
}

android {
    namespace = "team.brave.devevent.android.data"
}

dependencies {
    implementations(
        libs.bundles.fuel,
        libs.bundles.moshi,
        projects.domain,
    )
    testImplementations(
        libs.test.mockk,
        libs.test.coroutines,
    )
}
