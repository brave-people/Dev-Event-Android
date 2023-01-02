/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [JvmJUnit4Plugin.kt] created by Ji Sungbin on 22. 12. 7. 오후 9:18
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

@file:Suppress("unused")

import DependencyHandler.Extensions.testImplementations
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import team.brave.devevent.android.convention.libs
import team.brave.devevent.android.convention.setupJunit

internal class JvmJUnit4Plugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                setupJunit(
                    core = libs.findLibrary("test-junit-core").get(),
                    engine = libs.findLibrary("test-junit-engine").get(),
                )
                testImplementations(libs.findLibrary("test-strikt").get())
            }
        }
    }
}
