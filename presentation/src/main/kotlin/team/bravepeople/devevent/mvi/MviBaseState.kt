/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [MviBaseState.kt] created by Ji Sungbin on 21. 11. 11. 오후 6:28
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.mvi

interface MviBaseState {
    val loaded: Boolean
    val exception: Exception?

    fun isException() = exception != null
}
