/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [FancyItem.kt] created by Ji Sungbin on 21. 6. 22. 오후 4:38.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.ui.fancybottombar

import androidx.annotation.DrawableRes

data class FancyItem(val title: String = "", @DrawableRes val icon: Int? = null, val id: Int = 0)
