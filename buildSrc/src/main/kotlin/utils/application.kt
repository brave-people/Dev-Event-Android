/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [application.kt] created by Ji Sungbin on 22. 12. 7. 오후 9:17
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

@file:Suppress("UnstableApiUsage", "UnusedReceiverParameter")

package utils

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

internal fun Project.configureApplication(extension: CommonExtension<*, *, *, *>) {
    extension.apply {
        compileSdk = ApplicationConstants.compileSdk

        defaultConfig {
            minSdk = ApplicationConstants.minSdk
        }

        sourceSets {
            getByName("main").java.srcDirs("src/main/kotlin/")
        }

        compileOptions {
            sourceCompatibility = ApplicationConstants.javaVersion
            targetCompatibility = ApplicationConstants.javaVersion
        }

        lint {
            checkTestSources = true
        }

        dependencies.add("detektPlugins", libs.findLibrary("detekt-plugin-formatting").get().get())
    }
}
