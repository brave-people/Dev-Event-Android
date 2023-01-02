/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [MainViewModel.kt] created by Ji Sungbin on 23. 1. 2. 오후 7:26
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import team.brave.devevent.android.domain.usecase.GetAllEventsUseCase

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllEventsUseCase: GetAllEventsUseCase,
) : ViewModel()
