/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [collection.kt] created by Ji Sungbin on 23. 1. 22. 오전 4:51
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.presentation.util

fun <T> Set<T>.mutate(mutator: MutableSet<T>.() -> Unit): Set<T> {
    return toMutableSet().apply(mutator)
}
