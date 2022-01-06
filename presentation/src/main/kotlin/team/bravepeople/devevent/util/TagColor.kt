/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [Color.kt] created by Ji Sungbin on 21. 6. 29. 오전 4:40.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.util

import android.content.Context
import team.bravepeople.devevent.util.constant.DataConstant

object TagColor {
    private val RandomColor get() = (Math.random() * 16777215).toInt() or (0xFF shl 24)
    private val tagColors: HashMap<String, Int> = hashMapOf()

    operator fun invoke(context: Context, tag: String): Int {
        var color = tagColors[tag]
        if (color == null) {
            val savedColor = DataUtil.read(context, DataConstant.TagColor(tag), null)?.toInt()
            if (savedColor != null) {
                color = savedColor
                tagColors[tag] = color
            } else {
                color = RandomColor
                DataUtil.save(context, DataConstant.TagColor(tag), color.toString())
            }
        }
        return color
    }
}
