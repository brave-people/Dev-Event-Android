/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [AndroidHiltPlugin.kt] created by Ji Sungbin on 22. 12. 7. 오후 9:17
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

@file:Suppress("unused")

import DependencyHandler.Extensions.implementations
import DependencyHandler.Extensions.kapts
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import team.brave.devevent.android.convention.PluginEnum
import team.brave.devevent.android.convention.applyPlugins
import team.brave.devevent.android.convention.libs

internal class AndroidHiltPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugins(
                PluginEnum.KotlinKapt,
                libs.findPlugin("di-hilt").get().get().pluginId,
            )

            dependencies {
                implementations(libs.findLibrary("di-hilt-core").get())
                kapts(libs.findLibrary("di-hilt-compiler").get())
            }
        }
    }
}
