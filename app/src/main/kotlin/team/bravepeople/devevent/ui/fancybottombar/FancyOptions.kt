/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [FancyOptions.kt] created by Ji Sungbin on 21. 6. 22. 오후 4:39.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.ui.fancybottombar

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class FancyOptions(
    var fontFamily: FontFamily = FontFamily.Default,
    var indicatorHeight: Dp = 1.dp,
    var barHeight: Dp = 60.dp,
    var titleTopPadding: Dp = 4.dp
)
