/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [settings.gradle.kts] created by Ji Sungbin on 22. 12. 7. 오후 9:18
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

@file:Suppress("UnstableApiUsage")

rootProject.name = "buildSrc"

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }

    pluginManagement {
        repositories {
            mavenCentral()
            gradlePluginPortal()
        }
    }

    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}
