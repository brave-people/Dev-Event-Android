/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [build.gradle.kts] created by Ji Sungbin on 21. 6. 20. 오후 11:52.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    // id("kotlinx-serialization")
    id("dagger.hilt.android.plugin")
    id("name.remal.check-dependency-updates") version Versions.Util.CheckDependencyUpdates
}

android {
    compileSdk = Application.compileSdk

    defaultConfig {
        minSdk = Application.minSdk
        targetSdk = Application.targetSdk
        versionCode = Application.versionCode
        versionName = Application.versionName
        multiDexEnabled = true
        setProperty("archivesBaseName", "$versionName ($versionCode)")

        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }

            javacOptions {
                option("-Adagger.fastInit=ENABLED")
                option("-Adagger.hilt.android.internal.disableAndroidSuperclassValidation=true")
            }
        }

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        compose = true
    }

    kapt {
        correctErrorTypes = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Compose.Master
    }

    sourceSets {
        getByName("main").run {
            java.srcDirs("src/main/kotlin")
            resources.excludes.add("META-INF/library_release.kotlin_module")
        }
    }

    buildTypes {
        release {
            isDebuggable = false
            isMinifyEnabled = true
        }
    }

    compileOptions {
        sourceCompatibility = Application.sourceCompat
        targetCompatibility = Application.targetCompat
    }

    kotlinOptions {
        jvmTarget = Application.jvmTarget
    }
}

dependencies {
    Dependencies.debug.forEach(::debugImplementation)
    Dependencies.essential.forEach(::implementation)
    Dependencies.network.forEach(::implementation)
    Dependencies.ui.forEach(::implementation)
    Dependencies.util.forEach(::implementation)
    Dependencies.compose.forEach(::implementation)
    Dependencies.hilt.forEach(::implementation)
    Dependencies.room.forEach(::implementation)
    Dependencies.compiler.forEach(::kapt)
}
