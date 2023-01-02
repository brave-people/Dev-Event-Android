/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [settings.gradle.kts] created by Ji Sungbin on 23. 1. 1. 오후 3:35
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

@file:Suppress("UnstableApiUsage")

rootProject.name = "DevEvent-Android"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }

    includeBuild("build-logic")
    includeBuild("build-logic/local-enums")
}

buildCache {
    local {
        removeUnusedEntriesAfterDays = 7
    }
}

include(
    ":app",
    ":data",
    ":domain",
    ":presentation",
    ":datastore",
    ":di",
)
