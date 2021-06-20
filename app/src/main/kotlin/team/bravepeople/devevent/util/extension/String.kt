/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [String.kt] created by Ji Sungbin on 21. 6. 20. 오후 11:57.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.util.extension

import com.google.gson.Gson

inline fun <reified T> String.toModel(clazz: Class<T>) = Gson().fromJson(this, clazz)!!
