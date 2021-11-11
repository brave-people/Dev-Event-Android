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
    id("com.google.devtools.ksp")
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
        }
    }

    buildTypes {
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
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
    implementation(Dependencies.Orbit)
    implementation(Dependencies.LandscapistCoil) {
        exclude(group = "androidx.appcompat", module = "appcompat")
        exclude(group = "androidx.appcompat", module = "appcompat-resources")
    }

    Dependencies.Ui.forEach(::implementation)
    Dependencies.Util.forEach(::implementation)
    Dependencies.Compose.forEach(::implementation)
    Dependencies.Jetpack.forEach(::implementation)
    Dependencies.Retrofit.forEach(::implementation)
    Dependencies.Essential.forEach(::implementation)

    Dependencies.Compiler.forEach(::ksp)
}
