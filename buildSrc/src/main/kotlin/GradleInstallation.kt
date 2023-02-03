/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [GradleInstallation.kt] created by Ji Sungbin on 23. 2. 4. 오전 7:28
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import utils.ApplicationConstants
import utils.PluginEnum
import utils.applyPlugins
import utils.configureApplication
import utils.libs
import utils.setupJunit

// Unsupported receiver value: Cxt { context(org.gradle.api.Project) public final fun android() }
object GradleInstallation {
    fun android(project: Project, block: BaseAppModuleExtension.() -> Unit = {}) {
        with(project) {
            applyPlugins(
                PluginEnum.AndroidApplication,
                PluginEnum.KotlinAndroid,
            )

            extensions.configure<BaseAppModuleExtension> {
                configureApplication(this)

                buildFeatures {
                    buildConfig = false
                }

                defaultConfig {
                    targetSdk = ApplicationConstants.targetSdk
                    versionName = ApplicationConstants.versionName
                    versionCode = ApplicationConstants.versionCode
                }

                block()
            }
        }
    }

    fun library(project: Project, block: LibraryExtension.() -> Unit = {}) {
        with(project) {
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

                block()
            }
        }
    }

    fun junit(project: Project) {
        with(project) {
            dependencies {
                setupJunit(
                    core = libs.findLibrary("test-junit-core").get(),
                    engine = libs.findLibrary("test-junit-engine").get(),
                )
                add("testImplementation", libs.findLibrary("test-strikt").get())
            }
        }
    }

    fun hilt(project: Project) {
        with(project) {
            applyPlugins(
                PluginEnum.KotlinKapt,
                libs.findPlugin("di-hilt").get().get().pluginId,
            )

            dependencies {
                add("implementation", libs.findLibrary("di-hilt-core").get())
                add("kapt", libs.findLibrary("di-hilt-compiler").get())
            }
        }
    }
}
