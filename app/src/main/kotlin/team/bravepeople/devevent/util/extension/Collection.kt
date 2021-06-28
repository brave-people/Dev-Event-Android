/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [Collection.kt] created by Ji Sungbin on 21. 6. 29. 오전 5:31.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.util.extension

fun <E> Collection<E>.takeIfSizeToCategory(n: Int) =
    if (size >= n) "${take(n).joinToString(",")}…" else this.joinToString(",")