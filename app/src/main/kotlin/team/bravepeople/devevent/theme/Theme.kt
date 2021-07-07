/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [Theme.kt] created by Ji Sungbin on 21. 6. 20. 오후 11:55.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.theme

import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val ColorOrange = Color(0xFFF9A825)
val colors = lightColors().copy(
    primary = Color(0xFF1e88e5),
    primaryVariant = Color(0xFF005cb2),
    secondary = Color(0xFF6ab7ff)
)

@Composable
fun MaterialTheme(content: @Composable () -> Unit) {
    androidx.compose.material.MaterialTheme(
        colors = colors
    ) {
        content()
    }
}
