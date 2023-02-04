/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [build.gradle.kts] created by Ji Sungbin on 22. 12. 8. 오전 3:45
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

plugins {
    `android-library`
}

GradleInstallation.with(project) {
    library(namespace = "team.brave.devevent.android.datastore")
}

dependencies {
    api(libs.androidx.datastore)
}
