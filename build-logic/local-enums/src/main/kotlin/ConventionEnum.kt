/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [ConventionEnum.kt] created by Ji Sungbin on 22. 12. 7. 오후 9:16
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

import org.gradle.api.Plugin
import org.gradle.api.Project

class ConventionEnum : Plugin<Project> {
    override fun apply(target: Project) = Unit

    companion object {
        private const val prefix = "devevent"

        const val AndroidApplication = "$prefix.android.application"
        const val AndroidLibrary = "$prefix.android.library"

        const val AndroidHilt = "$prefix.android.hilt"

        const val JvmLibrary = "$prefix.jvm.library"
        const val JvmJUnit4 = "$prefix.jvm.junit4"
    }
}
