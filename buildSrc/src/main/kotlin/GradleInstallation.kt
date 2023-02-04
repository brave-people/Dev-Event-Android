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
import utils.applyPlugins
import utils.configureApplication
import utils.libs
import utils.setupJunit

interface GradleInstallationScope {
    fun android(block: BaseAppModuleExtension.() -> Unit = {})
    fun library(block: LibraryExtension.() -> Unit = {})
    fun library(namespace: String)
    fun jvm()
    fun junit()
    fun hilt()
}

private class GradleInstallationScopeImpl(private val project: Project) : GradleInstallationScope {
    override fun android(block: BaseAppModuleExtension.() -> Unit) {
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

    override fun library(block: LibraryExtension.() -> Unit) {
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

    override fun library(namespace: String) {
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

                this.namespace = namespace
            }
        }
    }

    override fun jvm() {
        TODO("Not yet implemented")
    }

    override fun junit() {
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

    override fun hilt() {
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

// Unsupported receiver value: Cxt { context(org.gradle.api.Project) }
object GradleInstallation {
    fun with(project: Project, block: GradleInstallationScope.() -> Unit) {
        GradleInstallationScopeImpl(project).block()
    }
}
