/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [MviSplashState.kt] created by Ji Sungbin on 21. 11. 11. 오후 6:29
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.activity.splash.mvi

import team.bravepeople.devevent.event.domain.Event
import team.bravepeople.devevent.mvi.MviBaseState

data class MviSplashState(
    override val loaded: Boolean = false,
    override val exception: Exception? = null,
    val events: List<Event> = emptyList()
) : MviBaseState
