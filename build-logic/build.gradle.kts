/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [build.gradle.kts] created by Ji Sungbin on 22. 12. 7. 오후 9:18
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

@file:Suppress("DSL_SCOPE_VIOLATION")

import DependencyHandler.Extensions.implementations

plugins {
    `kotlin-dsl`
    alias(libs.plugins.util.dependency.handler.extensions)
}

group = "team.brave.devevent.android.local"

dependencies {
    implementations(
        libs.kotlin.core,
        libs.build.gradle.agp,
        libs.build.dependency.handler.extensions,
    )
}

// Always stay in sync with buildSrc/ConventionEnum
private object ConventionEnum {
    private const val prefix = "devevent"

    const val AndroidApplication = "$prefix.android.application"
    const val AndroidLibrary = "$prefix.android.library"

    const val AndroidHilt = "$prefix.android.hilt"

    const val JvmLibrary = "$prefix.jvm.library"
    const val JvmJUnit4 = "$prefix.jvm.junit4"
}

gradlePlugin {
    plugins {
        register("androidHiltPlugin") {
            id = ConventionEnum.AndroidHilt
            implementationClass = "AndroidHiltPlugin"
        }
        register("androidApplicationPlugin") {
            id = ConventionEnum.AndroidApplication
            implementationClass = "AndroidApplicationPlugin"
        }
        register("androidLibraryPlugin") {
            id = ConventionEnum.AndroidLibrary
            implementationClass = "AndroidLibraryPlugin"
        }
        register("jvmJunit4Plugin") {
            id = ConventionEnum.JvmJUnit4
            implementationClass = "JvmJUnit4Plugin"
        }
    }
}
