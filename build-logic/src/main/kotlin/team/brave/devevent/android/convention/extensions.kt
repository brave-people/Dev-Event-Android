/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [extensions.kt] created by Ji Sungbin on 22. 12. 7. 오후 9:17
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

@file:Suppress("SameParameterValue")

package team.brave.devevent.android.convention

import DependencyHandler.Extensions.testImplementations
import DependencyHandler.Extensions.testRuntimeOnlys
import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.NamedDomainObjectContainerScope
import org.gradle.kotlin.dsl.getByType

internal val Project.libs
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun Project.applyPlugins(vararg plugins: String) {
    plugins.forEach { plugin ->
        pluginManager.apply(plugin)
    }
}

@Suppress("nothing_to_inline")
internal inline operator fun <T : Any, C : NamedDomainObjectContainer<T>> C.invoke(
    configuration: Action<NamedDomainObjectContainerScope<T>>,
) = apply { configuration.execute(NamedDomainObjectContainerScope.of(this)) }

internal fun DependencyHandler.setupJunit(core: Any, engine: Any) {
    testImplementations(core)
    testRuntimeOnlys(engine)
}
