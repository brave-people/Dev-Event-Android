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
    id("dagger.hilt.android.plugin")
    id("name.remal.check-dependency-updates") version Versions.Util.CheckDependencyUpdates
}

android {
    compileSdk = ApplicationConstants.compileSdk

    defaultConfig {
        versionCode = ApplicationConstants.versionCode
        versionName = ApplicationConstants.versionName
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Compose.Master
    }

    buildTypes {
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
        }
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(Dependencies.Orbit)
    implementation(Dependencies.Jetpack.Hilt)

    Dependencies.Ui.forEach(::implementation)
    Dependencies.Util.forEach(::implementation)
    Dependencies.Compose.forEach(::implementation)
    Dependencies.Essential.forEach(::implementation)
    Dependencies.Debug.forEach(::debugImplementation)

    kapt(Dependencies.Compiler.Hilt)
}
