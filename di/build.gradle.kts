import DependencyHandler.Extensions.implementations

/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [build.gradle.kts] created by Ji Sungbin on 23. 1. 1. 오후 3:35
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

plugins {
    id(ConventionEnum.AndroidLibrary)
    id(ConventionEnum.AndroidHilt)
}

android {
    namespace = "team.brave.devevent.android.di"
}

dependencies {
    implementations(
        projects.data,
        projects.domain,
    )
}
