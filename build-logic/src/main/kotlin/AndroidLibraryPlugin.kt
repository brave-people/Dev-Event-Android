/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [AndroidLibraryPlugin.kt] created by Ji Sungbin on 22. 12. 7. 오후 9:18
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

@file:Suppress("UnstableApiUsage", "unused")

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import team.brave.devevent.android.convention.ApplicationConstants
import team.brave.devevent.android.convention.PluginEnum
import team.brave.devevent.android.convention.applyPlugins
import team.brave.devevent.android.convention.configureApplication

internal class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugins(
                PluginEnum.AndroidLibrary,
                PluginEnum.KotlinAndroid,
            )

            extensions.configure<LibraryExtension> {
                configureApplication(this)

                buildFeatures {
                    buildConfig = false
                }

                defaultConfig.apply {
                    targetSdk = ApplicationConstants.targetSdk
                }
            }
        }
    }
}
