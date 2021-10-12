/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [String.kt] created by Ji Sungbin on 21. 6. 24. 오후 9:40.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.util.extension

fun String.parseOrNull(content: String) = if (contains(content)) split(content)[1] else null

fun String.takeIfLength(n: Int) = if (length > n) "${take(n)}…" else this
