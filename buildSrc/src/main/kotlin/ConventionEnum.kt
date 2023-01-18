/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [ConventionEnum.kt] created by Ji Sungbin on 23. 1. 19. 오전 1:44
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

// Always stay in sync with build-logic/ConventionEnum
object ConventionEnum {
    private const val prefix = "devevent"

    const val AndroidApplication = "$prefix.android.application"
    const val AndroidLibrary = "$prefix.android.library"

    const val AndroidHilt = "$prefix.android.hilt"

    const val JvmLibrary = "$prefix.jvm.library"
    const val JvmJUnit4 = "$prefix.jvm.junit4"
}
