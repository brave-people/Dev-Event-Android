/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [ApplicationConstants.kt] created by Ji Sungbin on 22. 12. 7. 오후 9:17
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package utils

import org.gradle.api.JavaVersion

internal object ApplicationConstants {
    const val minSdk = 21
    const val targetSdk = 33
    const val compileSdk = 33
    const val versionCode = 20002
    const val versionName = "2.0.0-dev02"
    val javaVersion = JavaVersion.VERSION_11
}
