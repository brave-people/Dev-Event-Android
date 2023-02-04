/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [build.gradle.kts] created by Ji Sungbin on 23. 1. 2. 오후 5:32
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

import DependencyHandler.Extensions.implementations

GradleInstallation.with(project) {
    library(namespace = "team.brave.devevent.android.di")
    hilt()
}

dependencies {
    implementations(
        projects.data,
        projects.domain,
    )
}
