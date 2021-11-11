/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [EventViewModel.kt] created by Ji Sungbin on 21. 6. 22. 오후 9:45.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.activity.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import team.bravepeople.devevent.activity.splash.mvi.MviSplashState
import team.bravepeople.devevent.event.domain.EventRepo
import team.bravepeople.devevent.util.extension.doWhen
import team.bravepeople.devevent.util.extension.toException
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val eventRepo: EventRepo) :
    ViewModel(),
    ContainerHost<MviSplashState, Unit> {

    override val container = container<MviSplashState, Unit>(MviSplashState())

    fun loadEvents() = intent {
        eventRepo.load(viewModelScope).doWhen(
            onSuccess = { events ->
                reduce {
                    state.copy(loaded = true, exception = null, events = events)
                }
            },
            onFailure = { throwable ->
                reduce {
                    state.copy(exception = throwable.toException())
                }
            }
        )
    }
}
