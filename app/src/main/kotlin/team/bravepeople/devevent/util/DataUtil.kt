/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [DataUtil.kt] created by Ji Sungbin on 21. 6. 28. 오후 9:35.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.util

import android.content.Context

object DataUtil {
    fun read(context: Context, name: String, _null: String?) =
        context.getSharedPreferences("pref", Context.MODE_PRIVATE).getString(name, _null)

    fun save(context: Context, name: String, value: String) {
        context.getSharedPreferences("pref", Context.MODE_PRIVATE).edit().run {
            putString(name, value)
            apply()
        }
    }

    fun clear(context: Context) {
        context.getSharedPreferences("pref", Context.MODE_PRIVATE).edit().run {
            clear()
            apply()
        }
    }
}
