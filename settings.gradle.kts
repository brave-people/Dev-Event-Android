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
)
