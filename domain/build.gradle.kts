/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [build.gradle.kts] created by Ji Sungbin on 22. 12. 7. 오후 9:22
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

plugins {
    id(ConventionEnum.AndroidLibrary)
}

android {
    namespace = "team.brave.devevent.android.domain"
}

dependencies {
    implementation(libs.di.inject)
}
